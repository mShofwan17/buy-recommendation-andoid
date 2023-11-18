package me.skripsi.data.models

import me.skripsi.data.naiveBayes.decimalFormat
import me.skripsi.data.naiveBayes.diskon
import me.skripsi.data.naiveBayes.kategori
import me.skripsi.data.naiveBayes.penjualan
import me.skripsi.data.naiveBayes.stok

data class DataUjiCalculate(
    val kodeBarang: String,
    val namaBarang: String,
    val kategori: String,
    val stok: String,
    val isDiskon: Boolean = false,
    val penjualan: String
){
    fun getKategori(items: List<DataTraining>): Double {
        return items.kategori(
            kategori = this.kategori
        ).size.toDouble().decimalFormat()
    }

    fun getStok(items: List<DataTraining>): Double {
        return items.stok(
            stok = this.stok
        ).size.toDouble().decimalFormat()
    }

    fun getDiskon(items: List<DataTraining>): Double {
        return items.diskon(
            isDiskon = this.isDiskon
        ).size.toDouble().decimalFormat()
    }

    fun getPenjualan(items: List<DataTraining>): Double {
        return items.penjualan(
            penjualan = this.penjualan
        ).size.toDouble().decimalFormat()
    }
}