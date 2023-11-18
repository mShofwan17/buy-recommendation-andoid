package me.skripsi.data.data_source

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import me.skripsi.data.jsonDataMentah
import me.skripsi.data.models.DataUji
import me.skripsi.data.models.toDataTrainingEntity
import me.skripsi.data.models.toDataUji
import me.skripsi.roomdb.BuyRecommendationDatabase
import me.skripsi.roomdb.entity.DataTransaksiEntity
import javax.inject.Inject

class FormUjiDataSource @Inject constructor(
    private val dbSource: BuyRecommendationDatabase
) : BaseDateSource() {
    suspend fun saveDataUji(items: List<DataUji>): Boolean = runBlocking {
        return@runBlocking validateResponse {
          async {
                items.forEach {  dbSource.dataUji().addData(it.toDataUjiEntity()) }
            }.await()
            return@validateResponse dbSource.dataUji().getAll().isNotEmpty()
        }
    }

    suspend fun updateDataUji(items: List<DataUji>): Boolean = runBlocking {
        return@runBlocking validateResponse {
            async {
                items.forEach {  dbSource.dataUji().updateData(it.toDataUjiEntity()) }
            }.await()
            return@validateResponse dbSource.dataUji().getAll().isNotEmpty()
        }
    }

    suspend fun getAllDataUji(): List<DataUji> = runBlocking {
        return@runBlocking validateResponse {
            val items = async { dbSource.dataUji().getAll() }.await()
            items.map { it.toDataUji() }
        }
    }
}