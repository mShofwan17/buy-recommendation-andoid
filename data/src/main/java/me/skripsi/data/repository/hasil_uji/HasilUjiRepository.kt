package me.skripsi.data.repository.hasil_uji

import me.skripsi.data.models.ResultNaiveBayes

interface HasilUjiRepository {
    suspend fun getResultNaiveBayes(): List<ResultNaiveBayes>
    suspend fun deleteAll(): Boolean
}