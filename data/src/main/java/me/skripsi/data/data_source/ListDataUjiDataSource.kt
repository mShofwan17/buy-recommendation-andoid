package me.skripsi.data.data_source

import kotlinx.coroutines.runBlocking
import me.skripsi.roomdb.BuyRecommendationDatabase
import me.skripsi.roomdb.entity.DataTrainingEntity
import me.skripsi.roomdb.entity.DataTransaksiEntity
import javax.inject.Inject

class ListDataUjiDataSource @Inject constructor(
    private val dbSource: BuyRecommendationDatabase
) : BaseDateSource() {
    suspend fun getDataTransaksi(): List<DataTransaksiEntity> = runBlocking {
        return@runBlocking validateResponse {
            dbSource.dataTransaksiDao().getAllTransaction()
        }
    }

    suspend fun getDataTraining(): List<DataTrainingEntity> = runBlocking {
        return@runBlocking validateResponse {
            dbSource.dataTrainingDao().getAllDataTraining()
        }
    }
}