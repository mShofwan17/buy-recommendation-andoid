package me.skripsi.domain.usecases.beranda

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.skripsi.data.repository.beranda.BerandaRepository
import me.skripsi.domain.utils.ResponseState
import javax.inject.Inject

class DeleteAllDataTrainingUseCase @Inject constructor(
    private val repository: BerandaRepository
) {
    operator fun invoke(): Flow<ResponseState<Boolean>> {
        return flow {
            emit(ResponseState.Loading())
            try {
                val result = repository.deleteAllDataTraining()
                 emit(ResponseState.Success(result))
            } catch (e: Exception) {
                emit(ResponseState.Error(message = e.message))
            }

        }.flowOn(Dispatchers.IO)
    }
}