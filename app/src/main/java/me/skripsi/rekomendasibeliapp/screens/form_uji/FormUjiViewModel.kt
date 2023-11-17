package me.skripsi.rekomendasibeliapp.screens.form_uji

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.skripsi.domain.ui_models.UiProductSelected
import me.skripsi.domain.usecases.form_uji.GetProductSelectedUseCase
import me.skripsi.rekomendasibeliapp.ui.UiState
import javax.inject.Inject

@HiltViewModel
class FormUjiViewModel @Inject constructor(
    private val getProductSelectedUseCase: GetProductSelectedUseCase
) : ViewModel() {
    private val _productState = MutableStateFlow<UiState<List<UiProductSelected>>>(UiState())
    val productState get() = _productState.asStateFlow()

    private val _selectedProducts = MutableStateFlow<List<UiProductSelected>>(listOf())
    val selectedProducts get() = _selectedProducts.asStateFlow()

    init {
        getProductSelected()
    }

    private fun getProductSelected() {
        viewModelScope.launch {
            getProductSelectedUseCase().collectLatest {
                _productState.update { state ->
                    if (it.isNotEmpty()){
                        _selectedProducts.value = it
                        state.success(it)
                    }
                    else state.error("Gagal mendapatkan data")
                }
            }
        }
    }

    fun updateSelectedProduct(kodeBarang: String?, isSelected: Boolean) {
        _selectedProducts.value = _selectedProducts.value.map {
            if (it.kodeBarang == kodeBarang) it.copy(isSelected = isSelected)
            else it
        }
    }

}