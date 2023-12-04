package me.skripsi.data.data_source

import android.util.Log
import com.opencsv.CSVReaderBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import me.skripsi.data.models.DataUji
import me.skripsi.data.models.toDataUji
import me.skripsi.data.naiveBayes.labeledDiskon
import me.skripsi.roomdb.BuyRecommendationDatabase
import java.io.FileReader
import javax.inject.Inject

class FormUjiDataSource @Inject constructor(
    private val dbSource: BuyRecommendationDatabase
) : BaseDateSource() {

    suspend fun insertDataUjiFromCsv(filePath: String): List<DataUji> = runBlocking {
        return@runBlocking validateResponse {
            val csvReader = withContext(Dispatchers.IO) {
                CSVReaderBuilder(FileReader(filePath)).build()
            }
            val lines = csvReader.readAll()
            csvReader.close()

             lines.drop(1).map {
                DataUji(
                    kodeBarang = it[0],
                    namaBarang = it[1],
                    golongan = it[2],
                    stok = it[3].toInt(),
                    isDiskon = it[4].toInt().labeledDiskon(),
                    penjualan = it[5].toInt()
                )
            }
        }
    }

    suspend fun saveDataUji(items: List<DataUji>): Boolean = runBlocking {
        return@runBlocking validateResponse {
            async {
                items.forEach {
                    dbSource.dataUji().addData(it.toDataUjiEntity())
                }
            }.await()
            return@validateResponse dbSource.dataUji().getAll().isNotEmpty()
        }
    }

    suspend fun updateDataUji(items: List<DataUji>): Boolean = runBlocking {
        return@runBlocking validateResponse {
            Log.i("TAG_DataUJi", "updateDataUji: ${items.map { it.toDataUjiEntity() }}")
            val rowUpdated = async {
                dbSource.dataUji().updateData(items.map { it.toDataUjiEntity() })
            }.await()

            Log.i("TAG_DataUJi", "updateDataUji: $rowUpdated")
            return@validateResponse rowUpdated != -1
        }
    }

    suspend fun getAllDataUji(): List<DataUji> = runBlocking {
        return@runBlocking validateResponse {
            val items = async { dbSource.dataUji().getAll() }.await()
            items.map { it.toDataUji() }
        }
    }
}