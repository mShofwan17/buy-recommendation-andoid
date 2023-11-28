package me.skripsi.rekomendasibeliapp.screens.beranda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.skripsi.domain.usecases.beranda.CheckIsDataExistUseCase
import me.skripsi.domain.usecases.beranda.InsertDataUseCase
import me.skripsi.domain.usecases.list_data.GetListDataTrainingUseCase
import me.skripsi.domain.utils.ResponseState
import me.skripsi.rekomendasibeliapp.ui.UiState
import javax.inject.Inject

@HiltViewModel
class BerandaViewModel @Inject constructor(
    private val checkIsDataExistUseCase: CheckIsDataExistUseCase,
    private val insertDataUseCase: InsertDataUseCase
) : ViewModel() {

    private val _insertDataState = MutableStateFlow<UiState<String>>(UiState())
    val insertDataState get() = _insertDataState.asStateFlow()

    private val _isDataExist = MutableStateFlow<Boolean?>(null)
    val isDataExist get() = _isDataExist

    init {
        checkIsDataExist()
    }

    private fun checkIsDataExist() {
        viewModelScope.launch {
            checkIsDataExistUseCase().collectLatest {
                if (it) {
                    _insertDataState.update { state ->
                        state.success("Sudah ada data")
                    }
                }

                isDataExist.value = it
            }
        }
    }

    fun insertDataTraining(filePath: String? = null) {
        viewModelScope.launch {
            _isDataExist.update { true }
            _insertDataState.update { uiState -> uiState.loading() }
            insertDataUseCase(filePath).collectLatest { result ->
                _insertDataState.update { uiState ->
                    when(result){
                        is ResponseState.Loading -> {
                            uiState.loading()
                        }
                        is ResponseState.Success -> {
                            uiState.success("Data berhasil tersimpan")
                        }
                        is ResponseState.Error -> {
                            uiState.error(result.message)
                        }

                        else -> UiState()
                    }
                }
            }
        }
    }
}