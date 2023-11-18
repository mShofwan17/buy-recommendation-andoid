package me.skripsi.rekomendasibeliapp.utils

sealed class DataUjiChangedState {
    data class Stok(val updatedValue : Int): DataUjiChangedState()
    data class Diskon(val updatedValue : Boolean): DataUjiChangedState()
    data class Penjualan(val updatedValue : Int): DataUjiChangedState()
}
