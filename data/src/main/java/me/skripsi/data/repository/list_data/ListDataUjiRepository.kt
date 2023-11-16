package me.skripsi.data.repository.list_data

import me.skripsi.data.models.DataTraining
import me.skripsi.data.models.DataTransaksi

interface ListDataUjiRepository {
    suspend fun getDataTransaksi() : List<DataTransaksi>
    suspend fun getDataTraining(): List<DataTraining>
}