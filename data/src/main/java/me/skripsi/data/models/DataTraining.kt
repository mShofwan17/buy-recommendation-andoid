package me.skripsi.data.models

data class DataTraining(
    val id: Long,
    val kodeBarang: String?,
    val namaBarang: String?,
    val golongan: String? = "",
    val stok: String = "",
    val isDiskon: Boolean,
    val penjualan: String,
    val pembelian: Boolean
)
