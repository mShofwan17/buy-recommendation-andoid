package me.skripsi.domain.ui_models

import me.skripsi.data.models.DataTraining
import me.skripsi.data.models.DataTransaksi

fun DataTransaksi.toUiDataTransaksi() : UiDataTransaksi{
    this.apply {
        return  UiDataTransaksi(
            id = id,
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = golongan,
            stok = stok,
            isDiskon = isDiskon,
            penjualan = penjualan,
            pembelian = pembelian
        )
    }
}
fun DataTraining.toUiDataTraining(): UiDataTraining {
    this.apply {
        return  UiDataTraining(
            id = id,
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = golongan,
            stok = stok,
            isDiskon = isDiskon,
            penjualan = penjualan,
            pembelian = pembelian
        )
    }
}