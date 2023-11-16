package me.skripsi.domain.usecases.list_data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.skripsi.data.models.DataTransaksi
import me.skripsi.data.repository.list_data.ListDataUjiRepository
import me.skripsi.domain.ui_models.UiDataTransaksi
import javax.inject.Inject

class GetListDataTransaksiUseCase @Inject constructor(
    private val repository: ListDataUjiRepository
) {
    operator fun invoke(): Flow<List<UiDataTransaksi>>{
        return flow {
            val dataTransaksi = repository.getDataTransaksi()
                .map {
                    UiDataTransaksi(
                        id = it.id,
                        kodeBarang = it.kodeBarang,
                        namaBarang = it.namaBarang,
                        golongan = it.golongan,
                        stok = it.stok,
                        isDiskon = it.isDiskon,
                        penjualan = it.penjualan,
                        pembelian = it.pembelian
                    )
                }
            emit(dataTransaksi)
        }.flowOn(Dispatchers.IO)
    }
}