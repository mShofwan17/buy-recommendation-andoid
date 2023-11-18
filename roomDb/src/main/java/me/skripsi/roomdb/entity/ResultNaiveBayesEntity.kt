package me.skripsi.roomdb.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(
    tableName = "tb_naive_bayes",
    indices = [Index(value = ["kodeBarang"], unique = true)]
)
data class ResultNaiveBayesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val kodeBarang: String,
    val positiveResult: BigDecimal,
    val negativeResult: BigDecimal,
    val result: Boolean,
)
