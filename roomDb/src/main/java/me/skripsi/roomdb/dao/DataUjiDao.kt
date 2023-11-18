package me.skripsi.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import me.skripsi.roomdb.entity.DataTransaksiEntity
import me.skripsi.roomdb.entity.DataUjiEntity

@Dao
interface DataUjiDao {
    @Query("SELECT * FROM tb_data_uji")
    suspend fun getAll(): List<DataUjiEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(dataTraining: DataUjiEntity): Long

    @Update
    suspend fun updateData(dataUjiEntity: DataUjiEntity): Int

    @Query("DELETE FROM tb_data_uji")
    suspend fun deleteAll(): Int
}