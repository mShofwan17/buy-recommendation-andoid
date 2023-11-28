package me.skripsi.data.repository.beranda

interface BerandaRepository {
    suspend fun isDataTransaksiExist() : Boolean
    suspend fun insertDataTraining(filePath: String?): Boolean
}