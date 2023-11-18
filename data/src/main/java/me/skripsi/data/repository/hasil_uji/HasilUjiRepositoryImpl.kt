package me.skripsi.data.repository.hasil_uji

import me.skripsi.data.data_source.ResultNaiveBayesDataSource
import me.skripsi.data.models.ResultNaiveBayes
import javax.inject.Inject

class HasilUjiRepositoryImpl @Inject constructor(
    private val dataSource: ResultNaiveBayesDataSource
): HasilUjiRepository {
    override suspend fun getResultNaiveBayes(): List<ResultNaiveBayes> {
        return dataSource.calculateNaiveBayes()
    }

    override suspend fun deleteAll(): Boolean {
        return dataSource.deleteAll()
    }

}