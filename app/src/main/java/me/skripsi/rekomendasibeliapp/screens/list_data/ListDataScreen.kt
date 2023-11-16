package me.skripsi.rekomendasibeliapp.screens.list_data

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.skripsi.domain.ui_models.UiDataTransaksi

@Composable
fun ListDataScreen(
    viewModel: ListDataViewModel = hiltViewModel()
) {
    val listTransaksiState = viewModel.dataTransaksiState.collectAsState()
    val context = LocalContext.current

    listTransaksiState.value.showUIComposable(
        onSuccess = {
            ListContentScreen(items = it)
        },
        onError = {
            Toast.makeText(context, "Gagalll", Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
fun ListContentScreen(items: List<UiDataTransaksi>) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items.size) {
            val item = items[it]
            Text(
                modifier = Modifier.clickable {
                    item.apply {
                        Toast.makeText(context, "$namaBarang $golongan, $stok  $penjualan $pembelian", Toast.LENGTH_SHORT).show()
                    }
                },
                text = "${item.namaBarang}",
                color = Color.Blue,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
    }
}