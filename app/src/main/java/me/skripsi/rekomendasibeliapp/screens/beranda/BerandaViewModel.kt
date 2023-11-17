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
import me.skripsi.rekomendasibeliapp.ui.UiState
import javax.inject.Inject

@HiltViewModel
class BerandaViewModel @Inject constructor(
    private val checkIsDataExistUseCase: CheckIsDataExistUseCase,
    private val insertDataUseCase: InsertDataUseCase
) : ViewModel() {

    private val _insertDataState = MutableStateFlow<UiState<String>>(UiState())
    val insertDataState get() = _insertDataState.asStateFlow()

    init {
        checkIsDataExist()
    }

    private fun checkIsDataExist() {
        viewModelScope.launch {
            checkIsDataExistUseCase().collectLatest {
                if (!it) insertDataTraining()
                else _insertDataState.update { state ->
                    state.success("Sudah ada data")
                }
            }
        }
    }

    private fun insertDataTraining() {
        viewModelScope.launch {
            insertDataUseCase().collectLatest { result ->
                _insertDataState.update { uiState ->
                    if (result) uiState.success("Data berhasil tersimpan")
                    else uiState.error("Gagal")
                }
            }
        }
    }
}