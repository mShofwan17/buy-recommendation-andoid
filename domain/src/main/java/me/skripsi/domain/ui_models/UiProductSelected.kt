package me.skripsi.domain.ui_models

data class UiProductSelected(
    val kodeBarang: String?,
    val namaBarang: String?,
    val kategori: String?,
    var isSelected: Boolean = false
)