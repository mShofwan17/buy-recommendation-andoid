package me.skripsi.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import me.skripsi.roomdb.dao.DataTrainingDao
import me.skripsi.roomdb.dao.DataTransaksiDao
import me.skripsi.roomdb.dao.DataUjiDao
import me.skripsi.roomdb.entity.DataTrainingEntity
import me.skripsi.roomdb.entity.DataTransaksiEntity
import me.skripsi.roomdb.entity.DataUjiEntity

@Database(
    entities = [
        DataTrainingEntity::class,
        DataTransaksiEntity::class,
        DataUjiEntity::class
    ], version = 1, exportSchema = false
)
abstract class BuyRecommendationDatabase : RoomDatabase() {
    abstract fun dataTrainingDao(): DataTrainingDao
    abstract fun dataTransaksiDao(): DataTransaksiDao
    abstract fun dataUji(): DataUjiDao
}