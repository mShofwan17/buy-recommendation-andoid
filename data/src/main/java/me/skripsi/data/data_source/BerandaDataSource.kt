package me.skripsi.data.data_source

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import me.skripsi.data.jsonDataMentah
import me.skripsi.data.models.toDataTrainingEntity
import me.skripsi.roomdb.BuyRecommendationDatabase
import me.skripsi.roomdb.entity.DataTransaksiEntity
import javax.inject.Inject

class BerandaDataSource @Inject constructor(
    private val dbSource: BuyRecommendationDatabase
) : BaseDateSource() {
    suspend fun checkDataExist(): Boolean = runBlocking {
        return@runBlocking validateResponse {
            val items = async { dbSource.dataTransaksiDao().getAllTransaction() }.await()
            return@validateResponse items.isNotEmpty()
        }
    }

    suspend fun insertDataTraining(): Boolean {
        return validateResponse {
            val type = object : TypeToken<List<DataTransaksiEntity>>() {}.type
            val items = Gson().fromJson<List<DataTransaksiEntity>>(jsonDataMentah, type)

            items.onEach { dbSource.dataTransaksiDao().addDataTransaksi(it) }
                .onEach { transaksi ->
                    dbSource.dataTrainingDao().addDataTraining(transaksi.toDataTrainingEntity())
                }
           dbSource.dataTrainingDao().getAllDataTraining().isNotEmpty()
        }
    }
}