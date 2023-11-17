package me.skripsi.data.models

import me.skripsi.data.naiveBayes.classPembelian
import me.skripsi.data.naiveBayes.labeledDiskon
import me.skripsi.data.naiveBayes.labeledPenjualan
import me.skripsi.data.naiveBayes.labeledStok
import me.skripsi.roomdb.entity.DataTrainingEntity
import me.skripsi.roomdb.entity.DataTransaksiEntity

fun DataTransaksiEntity.toDataTransaksi() : DataTransaksi{
    this.apply {
        return  DataTransaksi(
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

fun DataTransaksiEntity.toDataTrainingEntity() : DataTrainingEntity {
    this.apply {
        return  DataTrainingEntity(
            id = id,
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = golongan,
            stok = stok.labeledStok(),
            isDiskon = isDiskon.toInt().labeledDiskon(),
            penjualan = penjualan.toInt().labeledPenjualan(),
            pembelian = pembelian.toInt().classPembelian()
        )
    }
}

fun DataTrainingEntity.toDataTraining(): DataTraining {
    this.apply {
        return  DataTraining(
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