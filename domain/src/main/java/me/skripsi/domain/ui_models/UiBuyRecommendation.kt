package me.skripsi.domain.ui_models

import me.skripsi.data.models.DetailResultNaiveBayes
import java.math.BigDecimal

data class UiBuyRecommendation(
    val dataTraining: UiDataTraining?,
    val positiveResult: BigDecimal,
    val negativeResult: BigDecimal,
    val result: Boolean,
    val recommendation: String,
    val positiveCalculate: DetailResultNaiveBayes,
    val negativeCalculate: DetailResultNaiveBayes
)
