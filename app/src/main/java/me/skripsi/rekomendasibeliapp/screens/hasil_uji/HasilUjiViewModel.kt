package me.skripsi.rekomendasibeliapp.screens.hasil_uji

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.skripsi.domain.ui_models.UiBuyRecommendation
import me.skripsi.domain.usecases.form_uji.GetAllDataUjiUseCase
import me.skripsi.domain.usecases.hasil_uji.DeleteAllUseCase
import me.skripsi.domain.usecases.hasil_uji.GetListHasilUjiUseCase
import me.skripsi.domain.utils.ResponseState
import me.skripsi.rekomendasibeliapp.ui.UiState
import javax.inject.Inject

@HiltViewModel
class HasilUjiViewModel @Inject constructor(
    private val getAllDataUjiUseCase: GetAllDataUjiUseCase,
    private val getListHasilUjiUseCase: GetListHasilUjiUseCase,
    private val deleteAllUseCase: DeleteAllUseCase
) : ViewModel() {
    private val _buyRecommendation = MutableStateFlow<UiState<List<UiBuyRecommendation>>>(UiState())
    val buyRecommendation get() = _buyRecommendation.asStateFlow()

    init {
        getBuyRecommendation()
    }

    private fun getBuyRecommendation(){
        viewModelScope.launch {
            val dataUjis = async { getAllDataUjiUseCase().single() }.await()
            getListHasilUjiUseCase(dataUjis).collectLatest {
                _buyRecommendation.update { state ->
                    when(it){
                        is ResponseState.Loading -> state.loading()
                        is ResponseState.Success -> state.success(it.data)
                        is ResponseState.Error -> state.error(it.message)
                    }
                }
            }
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            deleteAllUseCase().collectLatest {  }
        }
    }
}