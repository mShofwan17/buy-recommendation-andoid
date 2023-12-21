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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import me.skripsi.rekomendasibeliapp.R
import me.skripsi.rekomendasibeliapp.components.CardHome
import me.skripsi.rekomendasibeliapp.components.HtmlText
import me.skripsi.rekomendasibeliapp.components.LoadingContent
import me.skripsi.rekomendasibeliapp.components.MyButton
import me.skripsi.rekomendasibeliapp.navigation.Screens
import me.skripsi.rekomendasibeliapp.ui.UiState
import me.skripsi.rekomendasibeliapp.utils.constant.ContentConst
import me.skripsi.rekomendasibeliapp.utils.toRealPath

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BerandaScreen(
    navHostController: NavHostController,
    berandaViewModel: BerandaViewModel = hiltViewModel()
) {
    var openDialog by remember { mutableStateOf(false) }

    DeleteTrainingUI(
        viewModel = berandaViewModel,
        openDialog = openDialog,
        openDialogState = {
            openDialog = it
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.rekomendasi_beli),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue),
                actions = {
                    IconButton(
                        onClick = {
                            openDialog = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.ic_delete),
                            tint = Color.White
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        val context = LocalContext.current
        val isDataExist = berandaViewModel.isDataExist.collectAsState()
        val insertState = berandaViewModel.insertDataState.collectAsState()
        var actionFromImport by remember {
            mutableStateOf(false)
        }
        val resultFile = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                uri?.let {
                    val filePath = uri.toRealPath(context = context)
                    filePath?.let {
                        actionFromImport = true
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
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        text = "Selamat Datang di Aplikasi Rekomendasi Beli",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    HtmlText(
                        modifier = Modifier.fillMaxWidth()
                            .padding(bottom = 10.dp),
                        html = ContentConst.WELCOME_HTML
                    )
                    MyButton(
                        title = stringResource(R.string.import_data_training),
                        icon = Icons.Default.List,
                        backgroundColor = Color.Green
                    ) {
                        resultFile.launch("text/csv/*")
                    }
                }
            } else {
                StateBerandaContent(
                    navHostController = navHostController,
                    paddingValues = paddingValues,
                    state = insertState.value,
                    actionFromImport = actionFromImport,
                    viewModel = berandaViewModel
                )
            }
        }

    }
}

@Composable
fun StateBerandaContent(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    state: UiState<String>,
    actionFromImport: Boolean,
    viewModel: BerandaViewModel
) {
    val context = LocalContext.current

    state.showUIComposable(
        onLoading = {
            val label = if (!actionFromImport) "Download Data..."
            else "Import Data..."
            LoadingContent(
                modifier = Modifier.padding(paddingValues),
                labelLoading = label
            )
        },
        onSuccess = {
            BerandaContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White),
                navHostController = navHostController,
                viewModel
            )
        },
        onError = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    )
}

@Composable
fun DeleteTrainingUI(
    viewModel: BerandaViewModel,
    openDialog: Boolean,
    openDialogState: (Boolean) -> Unit = {}
) {
    val deleteResult by viewModel.deleteDataTraining.collectAsState()
    val context = LocalContext.current

    deleteResult.showUI(
        onSuccess = {
            viewModel.resetStateImport()
        },
        onError = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    )

    if (openDialog) {
        AlertDialog(
            title = { Text(text = stringResource(R.string.perhatian)) },
            text = { Text(text = stringResource(R.string.delete_training_msg)) },
            onDismissRequest = {
                openDialogState.invoke(false)
            },
            confirmButton = {
                TextButton(onClick = {
                    openDialogState.invoke(false)
                    viewModel.deleteAllDataTraining()
                }) {
                    Text(text = stringResource(R.string.ya))
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialogState.invoke(false) }) {
                    Text(text = stringResource(R.string.tidak))
                }
            }
        )
    }
}

@Composable
fun BerandaContent(
    modifier: Modifier,
    navHostController: NavHostController? = null,
    viewModel: BerandaViewModel
) {
    val scope = rememberCoroutineScope()
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
            /*CardHome(
                title = stringResource(R.string.data_transaksi),
                image = painterResource(id = R.drawable.transaction),
                onClick = {
                    navHostController?.navigate(Screens.ListData.passBoolean(true))
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))*/
            CardHome(
                title = stringResource(R.string.see_data_tranining),
                image = painterResource(id = R.drawable.training),
                onClick = {
                    navHostController?.navigate(Screens.ListData.passBoolean(false))
                }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            CardHome(
                title = stringResource(R.string.see_hasil_rekomendasi),
                image = painterResource(id = R.drawable.hasil),
                onClick = {
                    navHostController?.navigate(Screens.HasilUji.passBoolean())
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
                    scope.launch {
                        async { viewModel.deleteAll() }.await()
                        navHostController?.navigate(Screens.ProductSelected.route)
                    }
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
