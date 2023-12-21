package me.skripsi.rekomendasibeliapp.screens.hasil_uji

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import me.skripsi.domain.ui_models.UiBuyRecommendation
import me.skripsi.rekomendasibeliapp.R
import me.skripsi.rekomendasibeliapp.components.ContentFormAndResult
import me.skripsi.rekomendasibeliapp.components.LoadingContent
import me.skripsi.rekomendasibeliapp.components.MyButton
import me.skripsi.rekomendasibeliapp.navigation.Screens

@Composable
fun HasilUjiScreen(
    navHostController: NavHostController,
    viewModel: HasilUjiViewModel = hiltViewModel(),
    isFromHome: Boolean = false
) {
    val buyRecommendation by viewModel.buyRecommendation.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    if (!isFromHome) {
        BackHandler(true) {
            Toast.makeText(context, "Tidak bisa kembali", Toast.LENGTH_SHORT).show()
        }
    }

    buyRecommendation.showUIComposable(
        onLoading = {
            LoadingContent(
                modifier = Modifier.fillMaxSize(),
                labelLoading = if (!isFromHome) "Menghitung Naive Bayes..."
                else "Mendapatkan Data..."
            )
        },
        onSuccess = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                ListBuyRecommendation(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    items = it,
                    navController = navHostController
                )
                Spacer(modifier = Modifier.padding(top = 8.dp, end = 8.dp))
                MyButton(
                    title = stringResource(R.string.kembali_ke_beranda),
                    icon = Icons.Default.Home,
                    backgroundColor = Color.Blue
                ) {
                    scope.launch {
                        navHostController.navigate(Screens.Beranda.route) {
                            popUpTo(route = Screens.Beranda.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        },
        onError = {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
fun ListBuyRecommendation(
    modifier: Modifier = Modifier,
    items: List<UiBuyRecommendation>,
    navController: NavController
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items.size) {
            val item by remember { mutableStateOf(items[it]) }
            ContentFormAndResult(
                buyRecommendation = item,
                onClickContent = {
                    navController.navigate(Screens.DetailHasilUji.passKodeBarang(kodeBarang = item.dataTraining?.kodeBarang))
                }
            )
        }
    }
}