package me.skripsi.rekomendasibeliapp.screens.form_uji

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import me.skripsi.domain.ui_models.UiProductSelected
import me.skripsi.rekomendasibeliapp.components.CardProductSelected
import me.skripsi.rekomendasibeliapp.components.MyButton
import me.skripsi.rekomendasibeliapp.navigation.Screens

@Composable
fun ProductSelectedScreen(
    navHostController: NavHostController,
    viewModel: FormUjiViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val productState = viewModel.productState.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        productState.value.showUIComposable(
            onSuccess = {
                val selectedProduct by viewModel.selectedProducts.collectAsState()

                ListProductSelected(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    items = selectedProduct,
                    viewModel = viewModel
                )

                MyButton(
                    title = "Lanjut",
                    backgroundColor = Color.Blue
                ) {
                    scope.launch {
                        async {
                            val itemsSelected = selectedProduct.filter { it.isSelected }
                            viewModel.saveSelectedData(itemsSelected)
                        }.await()
                        navHostController.navigate(Screens.FormUji.route)
                        cancel()
                    }

                }
            },
            onError = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
        )
    }
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