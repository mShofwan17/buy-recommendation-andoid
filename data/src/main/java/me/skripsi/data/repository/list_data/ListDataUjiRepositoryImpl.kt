package me.skripsi.data.repository.list_data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import me.skripsi.data.jsonDataMentah
import me.skripsi.data.models.DataTraining
import me.skripsi.data.models.DataTransaksi
import me.skripsi.roomdb.BuyRecommendedDatabase
import me.skripsi.roomdb.entity.DataTransaksiEntity
import javax.inject.Inject

class ListDataUjiRepositoryImpl @Inject constructor(
    private val dbSource : BuyRecommendedDatabase
) : ListDataUjiRepository {
    override suspend fun getDataTransaksi(): List<DataTransaksi> = runBlocking {
        val check = async { dbSource.dataTransaksiDao().getAllTransaction() }
        if (check.await().isEmpty()) {
            val type = object : TypeToken<List<DataTransaksiEntity>>() {}.type
            val datas = Gson().fromJson<List<DataTransaksiEntity>>(jsonDataMentah, type)

            datas?.forEach {
                dbSource.dataTransaksiDao().addDataTransaksi(it)
            }
        }

        return@runBlocking dbSource.dataTransaksiDao().getAllTransaction().map {
            DataTransaksi(
                id = it.id,
                kodeBarang = it.kodeBarang,
                namaBarang = it.namaBarang,
                golongan = it.golongan,
                stok = it.stok,
                isDiskon = it.isDiskon,
                penjualan = it.penjualan,
                pembelian = it.pembelian
            )
        }
    }

    override suspend fun getDataTraining(): List<DataTraining> {
       return emptyList()
    }

}