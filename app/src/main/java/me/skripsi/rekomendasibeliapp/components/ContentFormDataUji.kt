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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.skripsi.domain.ui_models.UiDataUji
import me.skripsi.rekomendasibeliapp.R
import me.skripsi.rekomendasibeliapp.utils.VerticalSmallLabelBigContentState

@Composable
fun ContentFormDataUji(
    modifier: Modifier = Modifier,
    dataUji: UiDataUji
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    end = 12.dp,
                    start = 12.dp,
                    top = 12.dp,
                    bottom = 16.dp
                )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 16.dp),
                text = "#${dataUji.kodeBarang}",
                color = Color.Blue,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )

            VerticalSmallLabelBigContentLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                textLabel = stringResource(R.string.nama_barang),
                content = VerticalSmallLabelBigContentState.Text("${dataUji.namaBarang}")
            )

            VerticalSmallLabelBigContentLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                textLabel = stringResource(R.string.kategori_barang),
                content = VerticalSmallLabelBigContentState.Text("${dataUji.golongan}")
            )


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
                            text = "${dataUji.stok}",
                            onTextChange = {

                            }
                        )
                    }
                )
                Spacer(modifier = Modifier.padding(start =  2.dp , end = 2.dp))
                VerticalSmallLabelBigContentLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 8.dp),
                    textLabel = stringResource(R.string.diskon),
                    content = VerticalSmallLabelBigContentState.Spinner {
                        var isChecked by remember { mutableStateOf(dataUji.isDiskon) }
                        FormCheckBox(
                            modifier = Modifier.fillMaxWidth(),
                            isSelected = isChecked,
                            onClick = {
                                isChecked = it
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
                        text = "${dataUji.penjualan}",
                        onTextChange = {

                        }
                    )
                }
            )
        }

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
        penjualan = 0.0
    )

    ContentFormDataUji(dataUji = data)
}