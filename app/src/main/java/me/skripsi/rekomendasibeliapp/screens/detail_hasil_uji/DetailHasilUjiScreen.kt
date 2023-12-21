package me.skripsi.rekomendasibeliapp.screens.detail_hasil_uji

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.skripsi.domain.ui_models.UiBuyRecommendation
import me.skripsi.rekomendasibeliapp.components.GeneralTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailHasilUjiScreen(
    navController: NavController,
    viewModel: DetailHasilUjiViewModel = hiltViewModel()
) {
    val detailCalculate = viewModel.detailCalculate.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            GeneralTopAppBar(
                title = "Detail Perhitungan",
                onBackPressed = { navController.popBackStack() })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            detailCalculate.value.showUIComposable(
                onSuccess = {
                    DetailCalculateContent(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController,
                        uiBuyRecommendation = it
                    )
                },
                onError = {
                    Toast.makeText(context, "Gagal mendapatkan data", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Composable
fun DetailCalculateContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    uiBuyRecommendation: UiBuyRecommendation
) {
    Text(
        text = uiBuyRecommendation.toString()
    )
}