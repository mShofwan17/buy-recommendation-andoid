package me.skripsi.data.repository.beranda

import me.skripsi.data.data_source.BerandaDataSource
import javax.inject.Inject

class BerandaRepositoryImpl @Inject constructor(
    private val dataSource: BerandaDataSource
) : BerandaRepository {
    override suspend fun isDataTransaksiExist(): Boolean {
        return dataSource.checkDataExist()
    }

    override suspend fun insertDataTraining(filePath: String?): Boolean {
        return dataSource.insertDataTraining(filePath)
    }
}