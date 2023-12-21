package me.skripsi.data.data_source

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.opencsv.CSVReaderBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import me.skripsi.data.jsonDataMentah
import me.skripsi.data.models.DataTransaksi
import me.skripsi.data.models.toDataTrainingEntity
import me.skripsi.data.naiveBayes.safetyInt
import me.skripsi.roomdb.BuyRecommendationDatabase
import me.skripsi.roomdb.entity.DataTransaksiEntity
import java.io.FileReader
import javax.inject.Inject

class BerandaDataSource @Inject constructor(
    private val dbSource: BuyRecommendationDatabase
) : BaseDateSource() {
    suspend fun checkDataExist(): Boolean = runBlocking {
        return@runBlocking validateResponse {
            val items = async { dbSource.dataTransaksiDao().getAllTransaction() }
            items.await().isNotEmpty()
        }
    }

    suspend fun insertDataTraining(filePath: String?): Boolean = runBlocking {
        return@runBlocking validateResponse {
            val items = async {
                filePath?.let {
                    val csvReader = withContext(Dispatchers.IO) {
                        CSVReaderBuilder(FileReader(filePath)).build()
                    }
                    val lines = csvReader.readAll()
                    csvReader.close()


                    lines.drop(1).map {
                        DataTransaksi(
                            kodeBarang = it[0],
                            namaBarang = it[1],
                            golongan = it[2],
                            stok = it[3].toIntOrNull().safetyInt(),
                            isDiskon = it[4].toIntOrNull().safetyInt(),
                            penjualan = it[5].toIntOrNull().safetyInt(),
                            pembelian = it[6].toIntOrNull().safetyInt()
                        ).toDataTransaksiEntity()
                    }
                } ?: kotlin.run {
                    val type = object : TypeToken<List<DataTransaksiEntity>>() {}.type
                    Gson().fromJson(jsonDataMentah, type)
                }
            }

            items.await().onEach { dbSource.dataTransaksiDao().addDataTransaksi(it) }
                .onEach { transaksi ->
                    dbSource.dataTrainingDao().addDataTraining(transaksi.toDataTrainingEntity())
                }
            dbSource.dataTrainingDao().getAllDataTraining().isNotEmpty()
        }
    }

    fun deleteAll(): Boolean = runBlocking {
        validateResponse {
            val training = async { dbSource.dataTrainingDao().deleteAll() }
            val transaksi = async { dbSource.dataTransaksiDao().deleteAll() }

            training.await() != -1 && transaksi.await() != -1
        }
    }
}