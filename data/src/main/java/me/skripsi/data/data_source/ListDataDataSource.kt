package me.skripsi.data.data_source

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import me.skripsi.data.jsonDataMentah
import me.skripsi.data.models.DataTransaksi
import me.skripsi.roomdb.BuyRecommendationDatabase
import me.skripsi.roomdb.entity.DataTrainingEntity
import me.skripsi.roomdb.entity.DataTransaksiEntity
import javax.inject.Inject

class ListDataDataSource @Inject constructor(
    private val dbSource: BuyRecommendationDatabase
) : BaseDateSource() {
    suspend fun getDataTransaksi(): List<DataTransaksiEntity> = runBlocking {
        return@runBlocking validateResponse {
            val listType = object : TypeToken<List<DataTransaksiEntity>>() {}.type
            Gson().fromJson<List<DataTransaksiEntity>>(jsonDataMentah,listType)
        }
    }

    suspend fun getDataTraining(): List<DataTrainingEntity> = runBlocking {
        return@runBlocking validateResponse {
            dbSource.dataTrainingDao().getAllDataTraining()
        }
    }
}