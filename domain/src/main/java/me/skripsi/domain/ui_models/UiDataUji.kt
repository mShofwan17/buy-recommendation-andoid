package me.skripsi.domain.ui_models

import me.skripsi.data.models.DataUji

data class UiDataUji(
    val kodeBarang: String?,
    val namaBarang: String?,
    val golongan: String?,
    val stok: Int = 0,
    val isDiskon: Boolean = false,
    val penjualan: Double = 0.0,
){
    fun toDataUji(): DataUji{
        return DataUji(
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = golongan,
            stok = stok,
            isDiskon = isDiskon,
            penjualan = penjualan
        )
    }
}
