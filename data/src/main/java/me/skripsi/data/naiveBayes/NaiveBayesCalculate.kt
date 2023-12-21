package me.skripsi.data.naiveBayes

import me.skripsi.data.models.DataTraining
import me.skripsi.data.models.DataUjiCalculate
import me.skripsi.data.models.DetailResultNaiveBayes
import java.math.BigDecimal

object NaiveBayesCalculate {
    fun calculatePositive(
        dataUji: DataUjiCalculate,
        items: List<DataTraining>
    ): DetailResultNaiveBayes {
        items.apply {
            val positive = allTrueCount().toDouble()

            val kategori = (dataUji.getKategori(allTrue()) / positive).decimalFormat()
            val persediaan = (dataUji.getStok(allTrue()) / positive).decimalFormat()
            val promosi = (dataUji.getDiskon(allTrue()) / positive).decimalFormat()
            val penjualan = (dataUji.getPenjualan(allTrue()) / positive).decimalFormat()
            val positiveSize = (positive /size.toDouble()).decimalFormat()

            val result = persediaan * promosi * penjualan * positiveSize
            return DetailResultNaiveBayes(
                isPositive = true,
                kodeBarang = dataUji.kodeBarang,
                persediaan = persediaan.toBigDecimal(),
                diskon = promosi.toBigDecimal(),
                penjualan = penjualan.toBigDecimal(),
                size = positiveSize.toBigDecimal(),
                result = result.toBigDecimal().decimalFormat()
            )
        }
    }

    fun calculateNegative(
        dataUji: DataUjiCalculate,
        items: List<DataTraining>
    ): DetailResultNaiveBayes {
        items.apply {
            val negative = allFalseCount().toDouble()

            val kategori = (dataUji.getKategori(allFalse()) / negative).decimalFormat()
            val persediaan = (dataUji.getStok(allFalse()) / negative).decimalFormat()
            val promosi = (dataUji.getDiskon(allFalse()) / negative).decimalFormat()
            val penjualan = (dataUji.getPenjualan(allFalse()) / negative).decimalFormat()
            val negativeSize = (negative /size.toDouble()).decimalFormat()

            val result = persediaan * promosi * penjualan * negativeSize
            return DetailResultNaiveBayes(
                isPositive = false,
                kodeBarang = dataUji.kodeBarang,
                persediaan = persediaan.toBigDecimal(),
                diskon = promosi.toBigDecimal(),
                penjualan = penjualan.toBigDecimal(),
                size = negativeSize.toBigDecimal(),
                result = result.toBigDecimal().decimalFormat()
            )
        }
    }
    fun resultNaiveBayes(
        positive: BigDecimal,
        negative: BigDecimal
    ): Boolean {
        return positive >= negative
    }
}