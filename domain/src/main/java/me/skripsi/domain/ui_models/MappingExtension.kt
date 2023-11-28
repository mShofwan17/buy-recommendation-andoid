package me.skripsi.domain.ui_models

import me.skripsi.data.models.DataTraining
import me.skripsi.data.models.DataTransaksi
import me.skripsi.data.models.DataUji
import me.skripsi.data.models.ResultNaiveBayes
import me.skripsi.data.naiveBayes.recommendation

fun DataTransaksi.toUiDataTransaksi() : UiDataTransaksi{
    this.apply {
        return  UiDataTransaksi(
            id = id,
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = golongan,
            stok = stok,
            isDiskon = isDiskon.toShort(),
            penjualan = penjualan,
            pembelian = pembelian
        )
    }
}
fun DataTraining.toUiDataTraining(): UiDataTraining {
    this.apply {
        return  UiDataTraining(
            id = id,
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = golongan,
            stok = stok,
            isDiskon = isDiskon,
            penjualan = penjualan,
            pembelian = pembelian
        )
    }
}

fun DataTransaksi.toUiProductSelected(): UiProductSelected{
    this.apply {
        return UiProductSelected(
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            kategori = golongan
        )
    }
}

fun DataUji.toUiDataUji(): UiDataUji{
    this.apply {
        return UiDataUji(
            id= id,
            kodeBarang = kodeBarang,
            namaBarang = namaBarang,
            golongan = golongan,
            stok = stok,
            isDiskon = isDiskon,
            penjualan = penjualan
        )
    }
}

fun ResultNaiveBayes.toBuyRecommendation(dataTraining: UiDataTraining?): UiBuyRecommendation{
    this.apply {
        return UiBuyRecommendation(
            dataTraining = dataTraining,
            positiveResult = positiveResult,
            negativeResult = negativeResult,
            result = result,
            recommendation = result.recommendation()
        )
    }
}