package me.skripsi.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import me.skripsi.roomdb.dao.DataTrainingDao
import me.skripsi.roomdb.dao.DataTransaksiDao
import me.skripsi.roomdb.entity.DataTrainingEntity
import me.skripsi.roomdb.entity.DataTransaksiEntity

@Database(
    entities = [
        DataTrainingEntity::class,
        DataTransaksiEntity::class
    ], version = 1, exportSchema = false
)
abstract class BuyRecommendedDatabase : RoomDatabase() {
    abstract fun dataTrainingDao(): DataTrainingDao
    abstract fun dataTransaksiDao(): DataTransaksiDao
}