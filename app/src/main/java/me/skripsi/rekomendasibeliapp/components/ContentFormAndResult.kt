package me.skripsi.rekomendasibeliapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.skripsi.domain.ui_models.UiBuyRecommendation
import me.skripsi.domain.ui_models.UiDataUji
import me.skripsi.rekomendasibeliapp.R
import me.skripsi.rekomendasibeliapp.utils.DataUjiChangedState
import me.skripsi.rekomendasibeliapp.utils.VerticalSmallLabelBigContentState
import me.skripsi.rekomendasibeliapp.utils.viewDiskonLabel

@Composable
fun ContentFormAndResult(
    modifier: Modifier = Modifier,
    dataUji: UiDataUji? = null,
    buyRecommendation: UiBuyRecommendation? = null,
    onDataChange: (DataUjiChangedState) -> Unit = {}
) {
    val kode = dataUji?.kodeBarang ?: buyRecommendation?.dataTraining?.kodeBarang
    val nama = dataUji?.namaBarang ?: buyRecommendation?.dataTraining?.namaBarang
    val kategori = dataUji?.golongan ?: buyRecommendation?.dataTraining?.golongan

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .then(
                if (dataUji != null) Modifier.clip(shape = RoundedCornerShape(8.dp))
                else Modifier.clip(shape = RoundedCornerShape(12.dp))
            )

            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .then(
                    if (dataUji != null) Modifier.padding(
                        end = 12.dp,
                        start = 12.dp,
                        top = 12.dp,
                        bottom = 16.dp
                    ) else Modifier.padding(
                        end = 0.dp,
                        start = 0.dp,
                        top = 0.dp,
                        bottom = 0.dp
                    )
                )

        ) {
            buyRecommendation?.let {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    color = if (it.result) Color.Green
                    else Color.Red,
                    thickness = 12.dp,
                )
            }

            Column(
                modifier = Modifier.padding(
                    start = 12.dp,
                    end = 12.dp
                )
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 16.dp),
                    text = "#$kode",
                    color = Color.Blue,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )

                VerticalSmallLabelBigContentLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    textLabel = stringResource(R.string.nama_barang),
                    content = VerticalSmallLabelBigContentState.Text("$nama")
                )

                VerticalSmallLabelBigContentLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    textLabel = stringResource(R.string.kategori_barang),
                    content = VerticalSmallLabelBigContentState.Text("$kategori")
                )


                dataUji?.let {
                    FormDataUjiView(dataUji = it, onDataChange)
                }
            }


            buyRecommendation?.let {
                ResultBuyRecommendationView(item = it)
            }
        }

    }
}

@Composable
fun FormDataUjiView(
    dataUji: UiDataUji,
    onDataChange: (DataUjiChangedState) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        VerticalSmallLabelBigContentLayout(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(end = 8.dp),
            textLabel = stringResource(R.string.stok),
            content = VerticalSmallLabelBigContentState.InputText {
                MyInputText(
                    text = if (dataUji.stok == 0) ""
                    else dataUji.stok.toString(),
                    onTextChange = {
                        val convert = it.toIntOrNull()
                        convert?.let {
                            onDataChange(
                                DataUjiChangedState.Stok(convert)
                            )
                        }
                    }
                )
            }
        )
        Spacer(modifier = Modifier.padding(start = 2.dp, end = 2.dp))
        VerticalSmallLabelBigContentLayout(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 8.dp),
            textLabel = stringResource(R.string.diskon),
            content = VerticalSmallLabelBigContentState.Spinner {
                FormCheckBox(
                    modifier = Modifier.fillMaxWidth(),
                    isSelected = dataUji.isDiskon,
                    onClick = {
                        onDataChange.invoke(
                            DataUjiChangedState.Diskon(it)
                        )
                    }
                )
            }
        )
    }

    VerticalSmallLabelBigContentLayout(
        modifier = Modifier
            .fillMaxWidth(),
        textLabel = stringResource(R.string.penjualan),
        content = VerticalSmallLabelBigContentState.InputText {
            MyInputText(
                text = if (dataUji.penjualan == 0) ""
                else dataUji.penjualan.toString(),
                onTextChange = {
                    val convert = it.toIntOrNull()
                    convert?.let {
                        onDataChange(
                            DataUjiChangedState.Penjualan(convert)
                        )
                    }
                }
            )
        }
    )
}

@Composable
fun ResultBuyRecommendationView(
    item: UiBuyRecommendation
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 12.dp, end = 12.dp, bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        VerticalSmallLabelBigContentLayout(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            textLabel = stringResource(R.string.stok),
            content = VerticalSmallLabelBigContentState.Text("${item.dataTraining?.stok}")
        )
        Spacer(modifier = Modifier.padding(start = 2.dp, end = 2.dp))
        VerticalSmallLabelBigContentLayout(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            textLabel = stringResource(R.string.diskon),
            content =VerticalSmallLabelBigContentState.Text("${item.dataTraining?.isDiskon?.viewDiskonLabel()}")
        )
        Spacer(modifier = Modifier.padding(start = 2.dp, end = 2.dp))
        VerticalSmallLabelBigContentLayout(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) ,
            horizontalAlignment = Alignment.End,
            textLabel = stringResource(R.string.penjualan),
            content =VerticalSmallLabelBigContentState.Text("${item.dataTraining?.penjualan}")
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                if (item.result) Color.Green
                else Color.Red
            ),
        contentAlignment = Alignment.Center
    ){
        Text(
            modifier = Modifier
                .padding(all = 8.dp),
            text = item.recommendation,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )
    }

}

@Preview
@Composable
fun ContentFormDataUjiPrev() {
    val data = UiDataUji(
        kodeBarang = "0006585",
        namaBarang = "Chil Go Steril Coklat 140ml",
        golongan = "MAKANAN",
        stok = 0,
        isDiskon = false,
        penjualan = 0
    )

    ContentFormAndResult(dataUji = data) {

    }
}

@Preview
@Composable
fun BottomResultPrev() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                Color.Green
            ),
        contentAlignment = Alignment.Center
    ){
        Text(
            modifier = Modifier
                .padding(all = 6.dp),
            text = "Rekomendasi Beli",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
    }
}