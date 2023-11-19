package me.skripsi.data.repository.form_uji

import me.skripsi.data.data_source.FormUjiDataSource
import me.skripsi.data.models.DataUji
import javax.inject.Inject

class FormUjiRepositoryImpl @Inject constructor(
    private val dataSource: FormUjiDataSource
): FormUjiRepository {
    override suspend fun saveDataUji(items: List<DataUji>): Boolean {
        return dataSource.saveDataUji(items)
    }

    override suspend fun updateDataUji(items: List<DataUji>): Boolean {
       return dataSource.updateDataUji(items)
    }

    override suspend fun getAllDataUji(): List<DataUji> {
        return dataSource.getAllDataUji()
    }

    override suspend fun insertDataUjiFromCsv(filePath: String): List<DataUji> {
        return dataSource.insertDataUjiFromCsv(filePath)
    }

}