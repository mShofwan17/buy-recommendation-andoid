package me.skripsi.rekomendasibeliapp.screens.form_uji

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import me.skripsi.domain.ui_models.UiDataUji
import me.skripsi.domain.ui_models.UiProductSelected
import me.skripsi.rekomendasibeliapp.R
import me.skripsi.rekomendasibeliapp.components.CardProductSelected
import me.skripsi.rekomendasibeliapp.components.MyButton
import me.skripsi.rekomendasibeliapp.navigation.Screens
import me.skripsi.rekomendasibeliapp.ui.UiState
import me.skripsi.rekomendasibeliapp.utils.toRealPath

@Composable
fun ProductSelectedScreen(
    navHostController: NavHostController,
    viewModel: FormUjiViewModel = hiltViewModel()
) {
    val productState = viewModel.productState.collectAsState()
    val selectedProduct by viewModel.selectedProducts.collectAsState()
    val importDataUjiResults by viewModel.insertDataUjiFromCsv.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        StateButtonImportFromCsv(
            state = importDataUjiResults,
            scope = scope,
            viewModel = viewModel,
            navHostController = navHostController
        )

        StateListSelectedProduct(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            productState = productState,
            selectedProduct = selectedProduct,
            viewModel = viewModel
        )

        MyButton(
            title = stringResource(R.string.lanjut),
            backgroundColor = Color.Blue
        ) {
            val itemsSelected = selectedProduct.filter { it.isSelected }
            val dataUji = itemsSelected.map { it.toDataUji() }

            saveAndNavigateToForm(
                scope,
                viewModel,
                dataUji,
                navHostController
            )
        }
    }
}

@Composable
fun StateButtonImportFromCsv(
    modifier: Modifier = Modifier,
    state: UiState<List<UiDataUji>>,
    scope: CoroutineScope,
    viewModel: FormUjiViewModel,
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val isLoading by remember {
        mutableStateOf(state.isLoading)
    }
    val resultFile = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                val filePath = uri.toRealPath(context = context)
                filePath?.let {
                    Toast.makeText(context, uri.toRealPath(context), Toast.LENGTH_LONG).show()
                    viewModel.insertDataUjiFromCsv(filePath)
                }
            }

        }
    )

    Box(
        modifier = modifier
            .padding(
                top = 16.dp,
                bottom = 16.dp,
                end = 24.dp,
                start = 24.dp
            ),
        contentAlignment = Alignment.Center,
    ) {
        MyButton(
            title = stringResource(R.string.import_from_csv),
            icon = Icons.Default.List,
            backgroundColor = Color.Green,
            contentLoading = isLoading
        ) {
            resultFile.launch("text/csv/*")
        }
    }

    state.showUIComposable(
        onSuccess = { dataUji ->
            viewModel.resetStateInsertCsv()
            saveAndNavigateToForm(
                scope,
                viewModel,
                dataUji,
                navHostController
            )
        },
        onError = {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
fun StateListSelectedProduct(
    modifier: Modifier = Modifier,
    productState: State<UiState<List<UiProductSelected>>>,
    selectedProduct: List<UiProductSelected>,
    viewModel: FormUjiViewModel
) {
    val context = LocalContext.current

    productState.value.showUIComposable(
        onSuccess = {

            ListProductSelected(
                modifier = modifier,
                items = selectedProduct,
                viewModel = viewModel
            )


        },
        onError = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    )
}


@Composable
fun ListProductSelected(
    modifier: Modifier = Modifier,
    items: List<UiProductSelected>,
    viewModel: FormUjiViewModel
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items.size) {
            var item by remember { mutableStateOf(items[it]) }
            CardProductSelected(
                productSelected = item,
                onClick = {
                    item = item.copy(isSelected = it)
                    viewModel.updateSelectedProduct(item.kodeBarang, it)
                }
            )
        }
    }
}

private fun saveAndNavigateToForm(
    scope: CoroutineScope,
    viewModel: FormUjiViewModel,
    items: List<UiDataUji>,
    navHostController: NavHostController
) {
    scope.launch {
        async {
            viewModel.saveSelectedData(items)
        }.await()
        navHostController.navigate(Screens.FormUji.route)
        cancel()
    }
}