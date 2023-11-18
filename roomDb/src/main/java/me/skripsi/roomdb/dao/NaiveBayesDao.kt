package me.skripsi.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.skripsi.roomdb.entity.ResultNaiveBayesEntity

@Dao
interface NaiveBayesDao {
    @Query("SELECT * FROM tb_naive_bayes")
    suspend fun getAll(): List<ResultNaiveBayesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(items : List<ResultNaiveBayesEntity>): List<Long>

    @Query("DELETE FROM tb_naive_bayes")
    suspend fun deleteAll(): Int
}