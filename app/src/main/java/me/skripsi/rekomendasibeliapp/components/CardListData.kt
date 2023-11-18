package me.skripsi.rekomendasibeliapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.skripsi.domain.ui_models.UiDataTraining
import me.skripsi.domain.ui_models.UiDataTransaksi
import me.skripsi.rekomendasibeliapp.R
import me.skripsi.rekomendasibeliapp.ui.colorClass
import me.skripsi.rekomendasibeliapp.ui.colorProbabilitas
import me.skripsi.rekomendasibeliapp.ui.pembelianLabel

@Composable
fun CardListData(
    modifier: Modifier = Modifier,
    isDataTransaksi: Boolean,
    dataTraining: UiDataTraining? = null,
    dataTransaksi: UiDataTransaksi? = null
) {
    val namaBarang = if (isDataTransaksi) dataTransaksi?.namaBarang
    else dataTraining?.namaBarang

    val kategori = if (isDataTransaksi) dataTransaksi?.golongan
    else dataTraining?.golongan

    val stok = if (isDataTransaksi) dataTransaksi?.stok
    else dataTraining?.stok

    val penjualan = if (isDataTransaksi) dataTransaksi?.penjualan?.toInt()
    else dataTraining?.penjualan

    val pembelian = if (isDataTransaksi) dataTransaksi?.pembelian?.toInt().toString()
    else dataTraining?.pembelian?.toString()


    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(shape = RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(
            focusedElevation = 10.dp,
            hoveredElevation = 10.dp,
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = if (isDataTransaksi) Color.Blue.copy(alpha = 0.7f)
                else dataTraining?.pembelian.colorClass(),
                thickness = 12.dp
            )

            Column(
                modifier = Modifier.padding(14.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp),
                    text = "$namaBarang",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 28.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(end = 6.dp),
                        text = "$kategori",
                        color = Color.Black.copy(alpha = 0.5f),
                        fontSize = MaterialTheme.typography.titleSmall.fontSize
                    )

                    Icon(
                        modifier = Modifier
                            .width(10.dp)
                            .height(10.dp)
                            .padding(end = 6.dp),
                        painter = painterResource(id = R.drawable.baseline_circle_24),
                        contentDescription = stringResource(R.string.cirlce_icon)
                    )

                    SideBySideText(
                        textLabel = {
                            Text(
                                modifier = Modifier,
                                text = "Stok",
                                color = Color.Black.copy(alpha = 0.5f),
                                fontSize = MaterialTheme.typography.titleSmall.fontSize
                            )
                        },
                        textContent = {
                            Text(
                                modifier = Modifier.padding(end = 6.dp),
                                text = "$stok",
                                color = if (isDataTransaksi) Color.Black.copy(alpha = 0.5f)
                                else stok.toString().colorProbabilitas(),
                                fontSize = MaterialTheme.typography.titleSmall.fontSize
                            )
                        }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SideBySideText(
                        modifier = Modifier.weight(1f),
                        textLabel = {
                            Text(
                                modifier = Modifier,
                                text = "Penjualan",
                                color = Color.Black,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize
                            )
                        },
                        textContent = {
                            Text(
                                modifier = Modifier.padding(end = 6.dp),
                                text = "$penjualan",
                                fontWeight = FontWeight.Bold,
                                color = if (isDataTransaksi) Color.Black
                                else penjualan.toString().colorProbabilitas(),
                                fontSize = MaterialTheme.typography.titleSmall.fontSize
                            )
                        }
                    )

                    if (isDataTransaksi){
                        SideBySideText(
                            modifier = Modifier.weight(1f),
                            textLabel = {
                                Text(
                                    modifier = Modifier,
                                    text = "Pembelian",
                                    color = Color.Black,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                                )
                            },
                            textContent = {
                                Text(
                                    modifier = Modifier.padding(end = 6.dp),
                                    text = "$pembelian",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    fontSize = MaterialTheme.typography.titleSmall.fontSize
                                )
                            }
                        )
                    } else {
                        Text(
                            modifier = Modifier.padding(end = 6.dp),
                            text = pembelian.toBoolean().pembelianLabel(),
                            fontWeight = FontWeight.Bold,
                            color = pembelian.toBoolean().colorClass(),
                            fontSize = MaterialTheme.typography.titleSmall.fontSize
                        )
                    }

                }


            }

        }
    }
}

@Preview
@Composable
fun CardListDataPreview() {
    val training = UiDataTraining(
        id = 0L,
        namaBarang = "Barang 1",
        golongan = "MAKANAN",
        stok = "Banyak",
        isDiskon = true,
        penjualan = "Banyak",
        pembelian = false
    )

    val transaksi = UiDataTransaksi(
        id = 0L,
        namaBarang = "Barang 1",
        golongan = "MAKANAN",
        stok = 100,
        isDiskon = 1,
        penjualan = 40,
        pembelian = 10
    )

    Column(
    ) {
        CardListData(
            modifier = Modifier.padding(bottom = 8.dp),
            isDataTransaksi = false,
            dataTraining = training.copy(stok = "Sedikit", pembelian = true)
        )
        CardListData(
            modifier = Modifier.padding(bottom = 8.dp),
            isDataTransaksi = false,
            dataTraining = training
        )
        CardListData(
            isDataTransaksi = true,
            dataTransaksi = transaksi
        )
    }

}