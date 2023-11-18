package me.skripsi.roomdb.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tb_data_uji",
    indices = [Index(value = ["kodeBarang"], unique = true)]
)
data class DataUjiEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val kodeBarang: String?,
    val namaBarang: String?,
    val golongan: String? = "",
    val stok: Int = 0,
    val isDiskon: Boolean = false,
    val penjualan: Double = 0.0,
)
