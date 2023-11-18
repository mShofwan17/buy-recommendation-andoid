package me.skripsi.domain.usecases.form_uji

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.skripsi.data.repository.list_data.ListDataUjiRepository
import me.skripsi.domain.ui_models.UiProductSelected
import me.skripsi.domain.ui_models.toUiProductSelected
import javax.inject.Inject

class GetProductSelectedUseCase @Inject constructor(
    private val repository: ListDataUjiRepository
) {
    operator fun invoke():Flow<List<UiProductSelected>>{
        return flow {
            try {
                val result = repository.getDataTransaksi().map { it.toUiProductSelected() }
                emit(result)
            }catch (e:Exception){
                emit(listOf())
            }
        }.flowOn(Dispatchers.IO)
    }
}