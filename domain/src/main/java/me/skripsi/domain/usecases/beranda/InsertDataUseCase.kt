package me.skripsi.domain.usecases.beranda

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.skripsi.data.repository.beranda.BerandaRepository
import me.skripsi.domain.utils.ResponseState
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val repository: BerandaRepository
) {
    operator fun invoke(filePath: String?): Flow<ResponseState<Boolean>> {
        return flow {
            emit(ResponseState.Loading())
            try {
                val result = repository.insertDataTraining(filePath = filePath)

                Log.i("TAG_Exception", "usecase: ${result}")
                 emit(ResponseState.Success(result))
            } catch (e: Exception) {

                Log.i("TAG_Exception", "usecase: ${e.message}")
                emit(ResponseState.Error( message = e.message))
            }

        }.flowOn(Dispatchers.IO)
    }
}