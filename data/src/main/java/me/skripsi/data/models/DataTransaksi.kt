package me.skripsi.data.models

import me.skripsi.roomdb.entity.DataTransaksiEntity


data class DataTransaksi(
    val id: Long = 0L,
    val kodeBarang: String?,
    val namaBarang: String?,
    val golongan: String? = "",
    val stok: Int = 0,
    val isDiskon: Int = 0,
    val penjualan: Int = 0,
    val pembelian: Int = 0
){
    fun toDataTransaksiEntity() : DataTransaksiEntity{
        return  DataTransaksiEntity(
            id = id,
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = golongan,
            stok = stok,
            isDiskon = isDiskon.toShort(),
            penjualan = penjualan,
            pembelian = pembelian
        )
    }
}
