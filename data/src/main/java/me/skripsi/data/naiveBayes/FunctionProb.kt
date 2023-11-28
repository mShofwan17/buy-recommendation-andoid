package me.skripsi.data.naiveBayes

import java.math.BigDecimal
import java.text.DecimalFormat

fun Boolean.recommendation(): String {
    return if (this) "Rekomendasi Beli"
    else "Rekomendasi Tidak Beli"
}

fun Int.labeledStok(): String {
    return when {
        this <= 30 -> "Sedikit"
        this in 31..100 -> "Standar"
        else -> "Banyak"
    }
}

fun Int.labeledDiskon(): Boolean {
    return this == 1
}

fun Int.labeledPenjualan(): String {
    return when {
        this <= 50 -> "Sedikit"
        this in 51..100 -> "Standar"
        else -> "Banyak"
    }
}

fun Int.classPembelian(): Boolean {
    return this > 0
}

fun Double.decimalFormat(): Double {
    val df = DecimalFormat("#")
    df.maximumFractionDigits = 4

    return df.format(this).toDouble()
}

fun BigDecimal.decimalFormat(): BigDecimal {
    val df = DecimalFormat("#")
    df.maximumFractionDigits = 5

    return df.format(this).toBigDecimal()
}

fun Int?.safetyInt(): Int {
    return this ?: 0
}