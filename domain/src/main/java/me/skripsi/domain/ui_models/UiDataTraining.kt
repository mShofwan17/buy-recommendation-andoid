package me.skripsi.domain.ui_models

data class UiDataTraining(
    val id: Long,
    val kodeBarang: String? = null,
    val namaBarang: String?,
    val golongan: String? = "",
    val stok: String = "",
    val isDiskon: Boolean,
    val penjualan: String,
    val pembelian: Boolean
)
