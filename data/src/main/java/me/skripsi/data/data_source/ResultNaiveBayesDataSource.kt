package me.skripsi.data.data_source

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import me.skripsi.data.models.ResultNaiveBayes
import me.skripsi.data.models.toDataTraining
import me.skripsi.data.models.toDataUji
import me.skripsi.data.naiveBayes.NaiveBayesCalculate
import me.skripsi.roomdb.BuyRecommendationDatabase
import javax.inject.Inject

class ResultNaiveBayesDataSource @Inject constructor(
    private val dbSource: BuyRecommendationDatabase
): BaseDateSource() {
    fun calculateNaiveBayes(): List<ResultNaiveBayes> = runBlocking {
        validateResponse {
            val results = mutableListOf<ResultNaiveBayes>()
            val getDataTraining = async { dbSource.dataTrainingDao().getAllDataTraining().map { it.toDataTraining() } }.await()
            val getDataUji = async {
                dbSource.dataUji().getAll().map { it.toDataUji() }.map { it.toDataUjiCalculate() }
            }.await()

            async {
                getDataUji.forEach {
                    NaiveBayesCalculate.apply {
                        val positive =  calculatePositive(dataUji = it, items = getDataTraining)
                        val negative = calculateNegative(dataUji = it, items = getDataTraining)

                        val result = ResultNaiveBayes(
                            kodeBarang = it.kodeBarang,
                            positiveResult = positive,
                            negativeResult = negative,
                            result = resultNaiveBayes(positive, negative)
                        )
                        results.add(result)
                    }

                }
            }.await()

            async { dbSource.resultNaiveBayes().addData(results.map { it.toResultNaiveBayesEntity() }) }.await()

            results.toList()
        }
    }

    fun deleteAll(): Boolean = runBlocking {
        validateResponse {
            async { dbSource.dataUji().deleteAll() }.await()
            async { dbSource.resultNaiveBayes().deleteAll() }.await()
            true
        }
    }
}