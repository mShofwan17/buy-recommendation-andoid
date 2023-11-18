package me.skripsi.data.naiveBayes

import me.skripsi.data.models.DataUjiCalculate
import java.math.BigDecimal

class NaiveBayesCalculate(
    private val dataUji  : DataUjiCalculate
) {
    fun calculatePositive( ): BigDecimal {
        dataUji.items.apply {
            val positive = allTrueCount().toDouble()

            val kategori = (dataUji.getKategori() / positive).decimalFormat()
            val persediaan = (dataUji.getStok() / positive).decimalFormat()
            val promosi = (dataUji.getDiskon() / positive).decimalFormat()
            val penjualan = (dataUji.getPenjualan() / positive).decimalFormat()
            val positiveSize = (positive /size.toDouble()).decimalFormat()

            val result = kategori * persediaan * promosi * penjualan * positiveSize

            println("$kategori * $persediaan * $promosi * $penjualan * $positiveSize")
            println("Positive : $positive / $size")
            println("${dataUji.namaBarang} Kategori Positive : $kategori")
            println("${dataUji.namaBarang} persediaan Positive : $persediaan")
            println("${dataUji.namaBarang} promosi Positive : $promosi")
            println("${dataUji.namaBarang} penjualan Positive : $penjualan")
            println("${dataUji.namaBarang} Result Positive : $result")

            return result.toBigDecimal().decimalFormat()
            //return result
        }
    }

    fun calculateNegative(): BigDecimal {
        dataUji.items.apply {
            val negative = allFalseCount().toDouble()

            val kategori = (dataUji.getKategori() / negative).decimalFormat()
            val persediaan = (dataUji.getStok() / negative).decimalFormat()
            val promosi = (dataUji.getDiskon() / negative).decimalFormat()
            val penjualan = (dataUji.getPenjualan() / negative).decimalFormat()
            val negativeSize = (negative /size.toDouble()).decimalFormat()

            val result = kategori * persediaan * promosi * penjualan * negativeSize
            println("----------------------------------------------------------------")
            println("$kategori * $persediaan * $promosi * $penjualan * $negativeSize")
            println("Negative : $negative / $size")
            println("${dataUji.namaBarang} Kategori Negative : $kategori")
            println("${dataUji.namaBarang} persediaan Negative : $persediaan")
            println("${dataUji.namaBarang} promosi Negative : $promosi")
            println("${dataUji.namaBarang} penjualan Negative : $penjualan")
            println("${dataUji.namaBarang} Result Negative : $result")
            return result.toBigDecimal().decimalFormat()
            // return result
        }
    }

    fun resultNaiveBayes(): Boolean {
        return calculatePositive() >= calculateNegative()
    }
}