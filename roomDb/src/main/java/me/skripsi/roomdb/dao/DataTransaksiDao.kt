package me.skripsi.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import me.skripsi.roomdb.entity.DataTransaksiEntity

@Dao
interface DataTransaksiDao {
    @Query("SELECT * FROM tb_data_transaksi")
    suspend fun getAllTransaction(): List<DataTransaksiEntity>

    @Delete
    suspend fun delete(dataTransaksiEntity: DataTransaksiEntity): Long
}