package me.skripsi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.skripsi.roomdb.entity.DataUjiEntity

data class DataUji(
    val kodeBarang: String?,
    val namaBarang: String?,
    val golongan: String?,
    val stok: Int = 0,
    val isDiskon: Boolean = false,
    val penjualan: Double = 0.0,
){
    fun toDataUjiEntity(): DataUjiEntity{
        return DataUjiEntity(
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = golongan,
            stok = stok,
            isDiskon = isDiskon,
            penjualan = penjualan
        )
    }
}
