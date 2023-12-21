package me.skripsi.rekomendasibeliapp.screens.detail_hasil_uji

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.skripsi.domain.ui_models.UiBuyRecommendation
import me.skripsi.rekomendasibeliapp.components.ContentDetailCalculate
import me.skripsi.rekomendasibeliapp.components.ContentHasilCalculate
import me.skripsi.rekomendasibeliapp.components.GeneralTopAppBar
import me.skripsi.rekomendasibeliapp.components.LoadingContent

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            detailCalculate.value.showUIComposable(
                onLoading = {
                    LoadingContent(labelLoading = "Mendapatkan Data...")
                },
                onSuccess = {
                    DetailCalculateContent(
                        modifier = Modifier.fillMaxSize(),
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
    uiBuyRecommendation: UiBuyRecommendation
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(
                top = 16.dp,
                bottom = 16.dp,
                start = 10.dp,
                end = 10.dp
            )
    ) {
        uiBuyRecommendation.apply {
            ContentDetailCalculate(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                item = positiveCalculate
            )
            Spacer(modifier = Modifier.padding(all = 8.dp))
            ContentDetailCalculate(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                item = negativeCalculate
            )
            Spacer(modifier = Modifier.padding(all = 8.dp))
            ContentHasilCalculate(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                item = this
            )

        }

    }


}