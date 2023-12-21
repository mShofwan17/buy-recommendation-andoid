package me.skripsi.rekomendasibeliapp.screens.detail_hasil_uji

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.skripsi.domain.ui_models.UiBuyRecommendation
import me.skripsi.domain.usecases.hasil_uji.GetDetailHasilUjiUseCase
import me.skripsi.domain.utils.ResponseState
import me.skripsi.rekomendasibeliapp.ui.UiState
import javax.inject.Inject

@HiltViewModel
class DetailHasilUjiViewModel @Inject constructor(
    private val getDetailHasilUjiUseCase: GetDetailHasilUjiUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailCalculate = MutableStateFlow<UiState<UiBuyRecommendation>>(UiState())
    val detailCalculate get() = _detailCalculate.asStateFlow()

    init {
        val kodeBarang: String? = savedStateHandle["kodeBarang"]
        kodeBarang?.let {
            getDetailCalculate(it)
        }
    }

    private fun getDetailCalculate(kodeBarang: String){
        viewModelScope.launch {
            getDetailHasilUjiUseCase(kodeBarang).collectLatest {
                _detailCalculate.update { state ->
                    when(it){
                        is ResponseState.Loading -> state.loading()
                        is ResponseState.Success -> state.success(it.data)
                        is ResponseState.Error -> state.error(it.message)
                    }
                }
            }
        }
    }
}