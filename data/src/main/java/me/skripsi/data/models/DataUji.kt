package me.skripsi.data.models

import me.skripsi.roomdb.entity.DataUjiEntity

data class DataUji(
    val id: Int = 0,
    val kodeBarang: String?,
    val namaBarang: String?,
    val golongan: String?,
    val stok: Int = 0,
    val isDiskon: Boolean = false,
    val penjualan: Double = 0.0,
){
    fun toDataUjiEntity(): DataUjiEntity{
        return DataUjiEntity(
            id = id.toLong(),
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = golongan,
            stok = stok,
            isDiskon = isDiskon,
            penjualan = penjualan
        )
    }
}
