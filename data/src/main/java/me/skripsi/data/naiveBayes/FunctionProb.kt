package me.skripsi.data.naiveBayes


fun Int.labeledStok(): String {
    return when {
        this <= 30 -> "Sedikit"
        this in 31..100 -> "Standar"
        else -> "Banyak"
    }
}

fun Int.labeledDiskon(): Boolean{
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