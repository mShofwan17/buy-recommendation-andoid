package me.skripsi.rekomendasibeliapp.screens.beranda

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import me.skripsi.rekomendasibeliapp.R
import me.skripsi.rekomendasibeliapp.components.CardHome
import me.skripsi.rekomendasibeliapp.components.LoadingContent
import me.skripsi.rekomendasibeliapp.components.MyButton
import me.skripsi.rekomendasibeliapp.navigation.Screens
import me.skripsi.rekomendasibeliapp.ui.UiState
import me.skripsi.rekomendasibeliapp.utils.toRealPath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BerandaScreen(
    navHostController: NavHostController,
    berandaViewModel: BerandaViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Rekomendasi Beli",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
            )
        },
    ) { paddingValues ->
        val context = LocalContext.current
        val isDataExist = berandaViewModel.isDataExist.collectAsState()
        val insertState = berandaViewModel.insertDataState.collectAsState()
        val resultFile = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                uri?.let {
                    val filePath = uri.toRealPath(context = context)
                    filePath?.let {
                        //Toast.makeText(context, uri.toRealPath(context), Toast.LENGTH_LONG).show()
                        berandaViewModel.insertDataTraining(filePath)
                    }
                }

            }
        )

        isDataExist.value?.let {
            if (!it) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 16.dp,
                            bottom = 16.dp,
                            end = 24.dp,
                            start = 24.dp
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    MyButton(
                        title = stringResource(R.string.import_from_csv),
                        icon = Icons.Default.List,
                        backgroundColor = Color.Green
                    ) {
                        resultFile.launch("text/csv/*")
                    }

                    Text(
                        modifier = Modifier.padding(
                            top = 10.dp,
                            bottom = 10.dp
                        ),
                        text = stringResource(R.string.or)
                    )

                    MyButton(
                        title = stringResource(R.string.download_data),
                        icon = Icons.Default.ArrowDropDown,
                        backgroundColor = Color.Blue
                    ) {
                        berandaViewModel.insertDataTraining(null)
                    }
                }
            } else {
                StateBerandaContent(
                    navHostController = navHostController,
                    paddingValues = paddingValues,
                    state = insertState.value
                )
            }
        }

    }
}

@Composable
fun StateBerandaContent(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    state: UiState<String>
) {
    val context = LocalContext.current

    state.showUIComposable(
        onLoading = {
            LoadingContent(
                modifier = Modifier.padding(paddingValues),
                labelLoading = "Download Data..."
            )
        },
        onSuccess = {
            BerandaContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White),
                navHostController = navHostController
            )
        },
        onError = {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
fun BerandaContent(
    modifier: Modifier,
    navHostController: NavHostController? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                    start = 10.dp,
                    end = 10.dp
                )
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .weight(1f, false),
        ) {
            CardHome(
                title = stringResource(R.string.data_transaksi),
                image = painterResource(id = R.drawable.transaction),
                onClick = {
                    navHostController?.navigate(Screens.ListData.passBoolean(true))
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            CardHome(
                title = stringResource(R.string.data_tranining),
                image = painterResource(id = R.drawable.training),
                onClick = {
                    navHostController?.navigate(Screens.ListData.passBoolean(false))
                }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            MyButton(
                icon = Icons.Default.PlayArrow,
                title = stringResource(R.string.uji_data),
                backgroundColor = Color.Blue,
                onClick = {
                    navHostController?.navigate(Screens.ProductSelected.route)
                }
            )
        }

    }
}

@Composable
@Preview
fun Preview() {
    // LoadingContent()
}
