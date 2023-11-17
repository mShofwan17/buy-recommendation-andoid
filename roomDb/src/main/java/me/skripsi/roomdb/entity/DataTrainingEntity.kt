package me.skripsi.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_data_training")
data class DataTrainingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val kodeBarang: String?,
    val namaBarang: String?,
    val golongan: String?,
    val stok: String,
    val isDiskon: Boolean = false,
    val penjualan: String,
    val pembelian: Boolean = false
)
