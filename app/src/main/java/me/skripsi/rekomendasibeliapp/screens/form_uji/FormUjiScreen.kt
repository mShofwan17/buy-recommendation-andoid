package me.skripsi.rekomendasibeliapp.screens.form_uji

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import me.skripsi.domain.ui_models.UiDataUji
import me.skripsi.rekomendasibeliapp.R
import me.skripsi.rekomendasibeliapp.components.ContentFormDataUji
import me.skripsi.rekomendasibeliapp.components.MyButton
import me.skripsi.rekomendasibeliapp.navigation.Screens
import me.skripsi.rekomendasibeliapp.utils.DataUjiChangedState

@Composable
fun FormUjiScreen(
    navHostController: NavHostController,
    viewModel: FormUjiViewModel = hiltViewModel()
) {
    val dataUjis by viewModel.dataUjis.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        ListFormDataUji(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            items = dataUjis,
            viewModel = viewModel
        )
        MyButton(
            title = stringResource(id = R.string.uji_data),
            icon = Icons.Default.CheckCircle,
            backgroundColor = Color.Blue
        ) {

            scope.launch {
                async { viewModel.updateDataUji(dataUjis) }.await()
                navHostController.navigate(Screens.HasilUji.route)
            }

        }
    }
}

@Composable
fun ListFormDataUji(
    modifier: Modifier = Modifier,
    items: List<UiDataUji>,
    viewModel: FormUjiViewModel
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items.size) {
            var item by remember { mutableStateOf(items[it]) }
            ContentFormDataUji(
                dataUji = item,
                onDataChange = {
                    it.apply {
                        item = when (this) {
                            is DataUjiChangedState.Stok -> item.copy(stok = updatedValue)
                            is DataUjiChangedState.Diskon -> item.copy(isDiskon = updatedValue)
                            is DataUjiChangedState.Penjualan -> item.copy(penjualan = updatedValue)
                        }
                        viewModel.updateChangeOnDataUji(item.kodeBarang, this)
                    }

                }
            )
        }
    }
}