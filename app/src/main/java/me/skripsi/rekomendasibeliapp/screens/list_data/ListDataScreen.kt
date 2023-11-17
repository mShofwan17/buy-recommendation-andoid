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
import androidx.navigation.NavController
import me.skripsi.domain.ui_models.UiDataTraining
import me.skripsi.domain.ui_models.UiDataTransaksi
import me.skripsi.rekomendasibeliapp.components.CardListData

@Composable
fun ListDataScreen(
    isFromTransaksi: Boolean? = true,
    navController: NavController,
    viewModel: ListDataViewModel = hiltViewModel()
) {
    val listTransaksiState = viewModel.dataTransaksiState.collectAsState()
    val listTrainingState = viewModel.dataTrainingState.collectAsState()
    val context = LocalContext.current

    isFromTransaksi?.let {
        if (isFromTransaksi){
            viewModel.getDataTransaksi()
            listTransaksiState.value.showUIComposable(
                onSuccess = {
                    ListContentTransaksi(items = it)
                },
                onError = {
                    Toast.makeText(context, "Gagalll", Toast.LENGTH_SHORT).show()
                }
            )
        }else{
            viewModel.getDataTraining()
            listTrainingState.value.showUIComposable(
                onSuccess = {
                    ListContentTraining(items = it)
                },
                onError = {
                    Toast.makeText(context, "Gagalll", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }?: run {  Toast.makeText(context, "ARGUMENT NULL BOSSS", Toast.LENGTH_SHORT).show() }
}

@Composable
fun ListContentTransaksi(items: List<UiDataTransaksi>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items.size) {
            val item = items[it]
            CardListData(isDataTransaksi = true, dataTransaksi = item)
        }
    }
}

@Composable
fun ListContentTraining(items: List<UiDataTraining>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items.size) {
            val item = items[it]
            CardListData(isDataTransaksi = false, dataTraining = item)
        }
    }
}