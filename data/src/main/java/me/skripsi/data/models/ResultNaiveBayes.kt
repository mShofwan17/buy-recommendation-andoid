package me.skripsi.data.models

import me.skripsi.roomdb.entity.ResultNaiveBayesEntity
import java.math.BigDecimal

data class ResultNaiveBayes(
    val kodeBarang: String,
    val positiveResult: BigDecimal,
    val negativeResult: BigDecimal,
    val result: Boolean,
    val detailPositive: DetailResultNaiveBayes,
    val detailNegative: DetailResultNaiveBayes,
){
    fun toResultNaiveBayesEntity() : ResultNaiveBayesEntity {
        return ResultNaiveBayesEntity(
            kodeBarang = kodeBarang,
            positiveResult = positiveResult,
            negativeResult = negativeResult,
            result = result
        )
    }
}
