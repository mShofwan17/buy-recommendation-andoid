package me.skripsi.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.skripsi.roomdb.entity.DataTrainingEntity
import me.skripsi.roomdb.entity.DataTransaksiEntity

@Dao
interface DataTrainingDao {
    @Query("SELECT * FROM tb_data_training")
    suspend fun getAllDataTraining(): List<DataTrainingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDataTraining(dataTraining: DataTrainingEntity): Long

    @Delete
    suspend fun delete(dataTraining: DataTrainingEntity): Int
}