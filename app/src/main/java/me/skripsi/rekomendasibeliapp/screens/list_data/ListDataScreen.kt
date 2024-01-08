package me.skripsi.rekomendasibeliapp.screens.list_data

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import me.skripsi.domain.ui_models.UiDataTraining
import me.skripsi.domain.ui_models.UiDataTransaksi
import me.skripsi.rekomendasibeliapp.R
import me.skripsi.rekomendasibeliapp.components.CardListData
import me.skripsi.rekomendasibeliapp.components.GeneralTopAppBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ListDataScreen(
    isFromTransaksi: Boolean? = true,
    navController: NavController,
    viewModel: ListDataViewModel = hiltViewModel()
) {
    val listTransaksiState = viewModel.dataTransaksiState.collectAsStateWithLifecycle()
    val listTrainingState = viewModel.dataTrainingState.collectAsState()
    val context = LocalContext.current

    val pullRefreshState = rememberPullRefreshState(
        refreshing = listTransaksiState.value.isLoading,
        onRefresh = { viewModel.getDataTransaksi() }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.apply {
            if (isFromTransaksi == true) getDataTransaksi()
            else getDataTraining()
        }
    }

    Scaffold(
        topBar = {
            GeneralTopAppBar(
                title = stringResource(id = if (isFromTransaksi == true) R.string.data_stok else R.string.data_tranining),
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    ) { padding ->
        isFromTransaksi?.let {
            if (isFromTransaksi) {

                listTransaksiState.value.showUIComposable(
                    onSuccess = {
                        ListContentTransaksi(
                            modifier = Modifier
                                .pullRefresh(pullRefreshState),
                            items = it,
                            paddingValues = padding
                        )


                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            PullRefreshIndicator(
                                refreshing = listTransaksiState.value.isLoading,
                                state = pullRefreshState
                            )
                        }

                    },
                    onError = {
                        Toast.makeText(context, "Gagalll", Toast.LENGTH_SHORT).show()
                    }
                )
            } else {

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
    modifier: Modifier = Modifier,
    items: List<UiDataTransaksi>,
    paddingValues: PaddingValues
) {
    Box(
        modifier = modifier
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