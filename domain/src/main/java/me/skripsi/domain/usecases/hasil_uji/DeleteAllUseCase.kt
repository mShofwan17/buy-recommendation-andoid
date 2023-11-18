package me.skripsi.domain.usecases.hasil_uji

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.skripsi.data.repository.hasil_uji.HasilUjiRepository
import javax.inject.Inject

class DeleteAllUseCase @Inject constructor(
    private val repository: HasilUjiRepository
) {
    operator fun invoke() : Flow<Boolean> {
        return flow {
            val result = repository.deleteAll()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}