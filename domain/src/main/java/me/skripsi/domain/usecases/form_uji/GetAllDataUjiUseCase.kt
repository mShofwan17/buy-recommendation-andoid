package me.skripsi.domain.usecases.form_uji

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.skripsi.data.models.DataUji
import me.skripsi.data.repository.form_uji.FormUjiRepository
import me.skripsi.domain.ui_models.UiDataUji
import me.skripsi.domain.ui_models.toUiDataUji
import javax.inject.Inject

class GetAllDataUjiUseCase @Inject constructor(
    private val repository: FormUjiRepository
) {
    operator fun invoke() : Flow<List<UiDataUji>> {
        return flow {
            val result = repository.getAllDataUji().map { it.toUiDataUji() }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}