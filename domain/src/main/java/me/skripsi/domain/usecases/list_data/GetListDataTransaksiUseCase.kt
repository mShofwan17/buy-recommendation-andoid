package me.skripsi.domain.usecases.list_data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.skripsi.data.models.DataTransaksi
import me.skripsi.data.repository.list_data.ListDataUjiRepository
import me.skripsi.domain.ui_models.UiDataTransaksi
import me.skripsi.domain.ui_models.toUiDataTransaksi
import javax.inject.Inject

class GetListDataTransaksiUseCase @Inject constructor(
    private val repository: ListDataUjiRepository
) {
    operator fun invoke(): Flow<List<UiDataTransaksi>> {
        return flow {
            try {
                val result = repository.getDataTransaksi().map { it.toUiDataTransaksi() }
                emit(result)
            } catch (e: Exception) {
                emit(listOf())
            }
        }.flowOn(Dispatchers.IO)
    }
}