package me.skripsi.domain.usecases.form_uji

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.skripsi.data.repository.form_uji.FormUjiRepository
import me.skripsi.domain.ui_models.UiDataUji
import me.skripsi.domain.ui_models.toUiDataUji
import me.skripsi.domain.utils.ResponseState
import javax.inject.Inject

class InsertDataUjiFromCsvUseCase @Inject constructor(
    private val repository: FormUjiRepository
) {
    operator fun invoke(filePath:String) : Flow<ResponseState<List<UiDataUji>>> {
        return flow {
            emit(ResponseState.Loading())
            try {
                val result = repository.insertDataUjiFromCsv(filePath = filePath).map { it.toUiDataUji() }
                emit(ResponseState.Success(result))
            }catch (e:Exception){
                Log.i("TAG_dataUji", "invoke: ${e.message}")
                emit(ResponseState.Error(e.message))
            }

        }.flowOn(Dispatchers.IO)
    }
}