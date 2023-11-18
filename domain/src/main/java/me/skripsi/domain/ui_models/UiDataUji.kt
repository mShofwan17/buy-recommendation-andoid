package me.skripsi.domain.ui_models

import me.skripsi.data.models.DataUji
import me.skripsi.data.naiveBayes.labeledPenjualan
import me.skripsi.data.naiveBayes.labeledStok

data class UiDataUji(
    val id: Int = 0,
    val kodeBarang: String?,
    val namaBarang: String?,
    val golongan: String?,
    val stok: Int = 0,
    val isDiskon: Boolean = false,
    val penjualan: Double = 0.0,
){
    fun toDataUji(): DataUji{
        return DataUji(
            id = id,
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = golongan,
            stok = stok,
            isDiskon = isDiskon,
            penjualan = penjualan
        )
    }

    fun toUiDataTraining(): UiDataTraining {
        return UiDataTraining(
            id = 0,
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = golongan,
            stok = stok.labeledStok(),
            isDiskon = isDiskon,
            penjualan = penjualan.toInt().labeledPenjualan(),
            pembelian = false
        )
    }
}
