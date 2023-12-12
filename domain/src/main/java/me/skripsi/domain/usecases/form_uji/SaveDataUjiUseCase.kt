package me.skripsi.domain.usecases.form_uji

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.skripsi.data.models.DataUji
import me.skripsi.data.repository.form_uji.FormUjiRepository
import me.skripsi.domain.ui_models.UiDataUji
import me.skripsi.domain.ui_models.toUiDataUji
import me.skripsi.domain.utils.ResponseState
import javax.inject.Inject

class SaveDataUjiUseCase @Inject constructor(
    private val repository: FormUjiRepository
) {
    operator fun invoke(items: List<UiDataUji>) : Flow<ResponseState<List<UiDataUji>>> {
        return flow {
            emit(ResponseState.Loading())
            try {
                val result = repository.saveDataUji(items.map { it.toDataUji() })
                emit(ResponseState.Success(result.map { it.toUiDataUji() }))
            }catch (e:Exception){
                Log.i("TAG_dataUji", "invoke: ${e.message}")
                emit(ResponseState.Error(e.message))
            }

        }.flowOn(Dispatchers.IO)
    }
}