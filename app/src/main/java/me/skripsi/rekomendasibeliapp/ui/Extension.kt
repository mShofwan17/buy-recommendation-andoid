package me.skripsi.rekomendasibeliapp.ui

import androidx.compose.ui.graphics.Color

fun Boolean?.colorClass(): Color {
    return if (this == true) Color.Green
    else Color.Red
}

fun String?.colorProbabilitas(): Color {
    return when (this) {
        "Banyak" -> Color.Green
        "Standar" -> Color.Blue
        else -> Color.Red
    }
}

fun Boolean.pembelianLabel(): String{
    return if (this) "Beli"
    else "Tidak Beli"
}