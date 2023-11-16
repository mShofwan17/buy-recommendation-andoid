package me.skripsi.domain.ui_models

data class UiDataTransaksi(
    val id: Long,
    val kodeBarang: String?,
    val namaBarang: String?,
    val golongan: String? = "",
    val stok: Int = 0,
    val isDiskon: Short = 0,
    val penjualan: Double = 0.0,
    val pembelian: Double = 0.0
)
