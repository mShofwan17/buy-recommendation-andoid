package me.skripsi.data.models


data class DataTransaksi(
    val id: Long,
    val kodeBarang: String?,
    val namaBarang: String?,
    val golongan: String? = "",
    val stok: Int = 0,
    val isDiskon: Short = 0,
    val penjualan: Int = 0,
    val pembelian: Int = 0
)
