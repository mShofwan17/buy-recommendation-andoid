package me.skripsi.rekomendasibeliapp.utils

fun Boolean.viewDiskonLabel(): String {
    return if (this) "Sedang Diskon"
    else "Tidak Diskon"
}