package me.skripsi.data.repository.list_data

import me.skripsi.data.data_source.ListDataDataSource
import me.skripsi.data.models.DataTraining
import me.skripsi.data.models.DataTransaksi
import me.skripsi.data.models.toDataTraining
import me.skripsi.data.models.toDataTransaksi
import javax.inject.Inject

class ListDataUjiRepositoryImpl @Inject constructor(
    private val dataSource: ListDataDataSource
) : ListDataUjiRepository {
    override suspend fun getDataTransaksi(): List<DataTransaksi> {
        return dataSource.getDataTransaksi().map { it.toDataTransaksi() }
    }

    override suspend fun getDataTraining(): List<DataTraining> {
        return dataSource.getDataTraining().map { it.toDataTraining() }
    }

}