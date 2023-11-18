package me.skripsi.domain.usecases.beranda

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.skripsi.data.repository.beranda.BerandaRepository
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: BerandaRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return flow {
            try {
                val result = repository.insertDataTraining()
                emit(result)
            } catch (e: Exception) {
                emit(false)
            }

        }.flowOn(Dispatchers.IO)
    }
}