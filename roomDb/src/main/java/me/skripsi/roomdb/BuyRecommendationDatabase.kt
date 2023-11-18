package me.skripsi.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.skripsi.roomdb.dao.DataTrainingDao
import me.skripsi.roomdb.dao.DataTransaksiDao
import me.skripsi.roomdb.dao.DataUjiDao
import me.skripsi.roomdb.dao.NaiveBayesDao
import me.skripsi.roomdb.entity.DataTrainingEntity
import me.skripsi.roomdb.entity.DataTransaksiEntity
import me.skripsi.roomdb.entity.DataUjiEntity
import me.skripsi.roomdb.entity.ResultNaiveBayesEntity

@Database(
    entities = [
        DataTrainingEntity::class,
        DataTransaksiEntity::class,
        DataUjiEntity::class,
        ResultNaiveBayesEntity::class
    ], version = 1, exportSchema = false
)
@TypeConverters(BigDecimalConverter::class)
abstract class BuyRecommendationDatabase : RoomDatabase() {
    abstract fun dataTrainingDao(): DataTrainingDao
    abstract fun dataTransaksiDao(): DataTransaksiDao
    abstract fun dataUji(): DataUjiDao
    abstract fun resultNaiveBayes(): NaiveBayesDao
}