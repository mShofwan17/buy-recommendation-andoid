package me.skripsi.data.naiveBayes

import me.skripsi.data.models.DataTraining


fun List<DataTraining>.allTrue(): List<DataTraining> =
    filter { it.pembelian }

fun List<DataTraining>.allFalse(): List<DataTraining> =
    filter { !it.pembelian }

fun List<DataTraining>.allTrueCount(): Int = allTrue().size
fun List<DataTraining>.allFalseCount(): Int = allFalse().size
fun List<DataTraining>.kategori(kategori: String): List<DataTraining> =
    this.filter { it.golongan == kategori }

fun List<DataTraining>.stok(stok: String): List<DataTraining> =
    this.filter { it.stok == stok }

fun List<DataTraining>.diskon(isDiskon: Boolean): List<DataTraining> =
    this.filter { it.isDiskon == isDiskon }

fun List<DataTraining>.penjualan(penjualan: String): List<DataTraining> =
    this.filter { it.penjualan == penjualan }