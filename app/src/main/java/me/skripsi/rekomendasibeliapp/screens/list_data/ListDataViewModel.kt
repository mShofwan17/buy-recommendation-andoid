package me.skripsi.rekomendasibeliapp.screens.list_data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.skripsi.domain.ui_models.UiDataTraining
import me.skripsi.domain.ui_models.UiDataTransaksi
import me.skripsi.domain.usecases.list_data.GetListDataTrainingUseCase
import me.skripsi.domain.usecases.list_data.GetListDataTransaksiUseCase
import me.skripsi.rekomendasibeliapp.ui.UiState
import javax.inject.Inject

@HiltViewModel
class ListDataViewModel @Inject constructor(
    private val getListDataTransaksiUseCase: GetListDataTransaksiUseCase,
    private val getListDataTrainingUseCase: GetListDataTrainingUseCase
) : ViewModel() {
    private val _dataTransaksiState = MutableStateFlow<UiState<List<UiDataTransaksi>>>(UiState())
    val dataTransaksiState get() = _dataTransaksiState.asStateFlow()

    private val _dataTrainingState = MutableStateFlow<UiState<List<UiDataTraining>>>(UiState())
    val dataTrainingState get() = _dataTrainingState.asStateFlow()


    fun getDataTransaksi() {
        viewModelScope.launch {
            getListDataTransaksiUseCase().collectLatest {
                _dataTransaksiState.update { state ->
                    state.success(it)
                }
            }
        }
    }

    fun getDataTraining() {
        viewModelScope.launch {
            getListDataTrainingUseCase().collectLatest {
                _dataTrainingState.update { state ->
                    state.success(it)
                }
            }
        }
    }
}