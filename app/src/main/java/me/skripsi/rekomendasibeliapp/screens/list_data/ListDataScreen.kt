package me.skripsi.rekomendasibeliapp.screens.list_data

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.skripsi.domain.ui_models.UiDataTraining
import me.skripsi.domain.ui_models.UiDataTransaksi
import me.skripsi.rekomendasibeliapp.R
import me.skripsi.rekomendasibeliapp.components.CardListData
import me.skripsi.rekomendasibeliapp.components.GeneralTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListDataScreen(
    isFromTransaksi: Boolean? = true,
    navController: NavController,
    viewModel: ListDataViewModel = hiltViewModel()
) {
    val listTransaksiState = viewModel.dataTransaksiState.collectAsState()
    val listTrainingState = viewModel.dataTrainingState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            GeneralTopAppBar(
                title = stringResource(id = R.string.data_tranining),
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    ) { padding ->
        isFromTransaksi?.let {
            if (isFromTransaksi) {
                viewModel.getDataTransaksi()
                listTransaksiState.value.showUIComposable(
                    onSuccess = {
                        ListContentTransaksi(items = it, paddingValues = padding)
                    },
                    onError = {
                        Toast.makeText(context, "Gagalll", Toast.LENGTH_SHORT).show()
                    }
                )
            } else {
                viewModel.getDataTraining()
                listTrainingState.value.showUIComposable(
                    onSuccess = {
                        ListContentTraining(items = it, paddingValues = padding)
                    },
                    onError = {
                        Toast.makeText(context, "Gagalll", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }


}

@Composable
fun ListContentTransaksi(
    items: List<UiDataTransaksi>,
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 10.dp,
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 10.dp
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(items.size) {
                val item = items[it]
                CardListData(isDataTransaksi = true, dataTransaksi = item)
            }
        }
    }
}

@Composable
fun ListContentTraining(
    items: List<UiDataTraining>,
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 10.dp,
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 10.dp
                ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(items.size) {
                val item = items[it]
                CardListData(isDataTransaksi = false, dataTraining = item)
            }
        }
    }

}