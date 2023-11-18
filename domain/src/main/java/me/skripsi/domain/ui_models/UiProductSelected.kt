package me.skripsi.domain.ui_models

import me.skripsi.data.models.DataUji

data class UiProductSelected(
    val kodeBarang: String?,
    val namaBarang: String?,
    val kategori: String?,
    var isSelected: Boolean = false
){
    fun toDataUji() : UiDataUji {
        return UiDataUji(
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = kategori
        )
    }
}