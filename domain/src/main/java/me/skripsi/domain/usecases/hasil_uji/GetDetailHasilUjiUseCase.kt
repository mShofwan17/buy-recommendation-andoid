package me.skripsi.domain.usecases.hasil_uji

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.skripsi.data.repository.hasil_uji.HasilUjiRepository
import me.skripsi.domain.ui_models.UiBuyRecommendation
import me.skripsi.domain.ui_models.toBuyRecommendation
import me.skripsi.domain.utils.ResponseState
import javax.inject.Inject

class GetDetailHasilUjiUseCase @Inject constructor(
    private val repository: HasilUjiRepository
) {
    operator fun invoke(kodeBarang: String): Flow<ResponseState<UiBuyRecommendation>> {
        return flow {
            emit(ResponseState.Loading())
            try {
                val result =
                    repository.getDetailResultNaiveBayes(kodeBarang)?.toBuyRecommendation(null)
                emit(ResponseState.Success(result))
            } catch (e: Exception) {
                Log.i("TAG_Exception", "invoke: ${e.message}")
                emit(ResponseState.Error(e.message))
            }
        }.flowOn(Dispatchers.IO)
    }
}