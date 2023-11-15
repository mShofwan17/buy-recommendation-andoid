package me.skripsi.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_data_transaksi")
data class DataTransaksiEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val kodeBarang: String?,
    val namaBarang: String?,
    val golongan: String? = "",
    val stok: Int = 0,
    val isDiskon: Short = 0,
    val penjualan: Double = 0.0,
    val pembelian: Double = 0.0
)
