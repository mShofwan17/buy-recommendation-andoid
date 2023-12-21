package me.skripsi.data.models

import me.skripsi.roomdb.entity.ResultNaiveBayesEntity
import java.math.BigDecimal

data class DetailResultNaiveBayes(
    val isPositive: Boolean,
    val kodeBarang: String,
    val persediaan: BigDecimal,
    val diskon: BigDecimal,
    val penjualan: BigDecimal,
    val size: BigDecimal,
    val result: BigDecimal
)
