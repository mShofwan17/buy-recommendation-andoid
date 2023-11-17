package me.skripsi.domain.usecases.list_data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.skripsi.data.repository.list_data.ListDataUjiRepository
import me.skripsi.domain.ui_models.UiDataTraining
import me.skripsi.domain.ui_models.toUiDataTraining
import javax.inject.Inject

class GetListDataTrainingUseCase @Inject constructor(
    private val repository: ListDataUjiRepository
) {
    operator fun invoke(): Flow<List<UiDataTraining>> {
        return flow {
            try {
                val result = repository.getDataTraining().map { it.toUiDataTraining() }
                emit(result)
            } catch (e: Exception) {
                emit(listOf())
            }
        }.flowOn(Dispatchers.IO)
    }
}