package me.skripsi.domain.ui_models

import java.math.BigDecimal

data class UiDetailResultNaiveBayes(
    val isPositive: Boolean,
    val kodeBarang: String,
    val persediaan: BigDecimal,
    val diskon: BigDecimal,
    val penjualan: BigDecimal,
    val size: BigDecimal,
    val result: BigDecimal
)
