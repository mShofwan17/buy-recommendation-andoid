package me.skripsi.data.repository.form_uji

import me.skripsi.data.models.DataUji

interface FormUjiRepository {
    suspend fun saveDataUji(items: List<DataUji>) : Boolean
    suspend fun updateDataUji(items: List<DataUji>): Boolean
    suspend fun getAllDataUji(): List<DataUji>
    suspend fun insertDataUjiFromCsv(filePath: String): List<DataUji>
}