package me.skripsi.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.skripsi.roomdb.entity.DataTransaksiEntity

@Dao
interface DataTransaksiDao {
    @Query("SELECT * FROM tb_data_transaksi")
    suspend fun getAllTransaction(): List<DataTransaksiEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDataTransaksi(dataTraining: DataTransaksiEntity): Long

    @Delete
    suspend fun delete(dataTransaksiEntity: DataTransaksiEntity): Int

    @Query("DELETE FROM tb_data_transaksi")
    suspend fun deleteAll(): Int
}