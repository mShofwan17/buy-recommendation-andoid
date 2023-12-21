package me.skripsi.rekomendasibeliapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.skripsi.domain.ui_models.UiBuyRecommendation
import me.skripsi.domain.ui_models.UiDetailResultNaiveBayes
import me.skripsi.rekomendasibeliapp.ui.theme.BackgroundColor200

@Composable
fun ContentDetailCalculate(
    modifier: Modifier,
    item: UiDetailResultNaiveBayes
) {

    val label = if (item.isPositive) "Hasil Class Beli"
    else "Hasil Class Tidak Beli"

    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(12.dp),
                color = BackgroundColor200
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = label
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ContentCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f),
                    isPositive = item.isPositive,
                    label = "Stok",
                    value = item.persediaan.toString()
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "X"
                )
                ContentCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f),
                    isPositive = item.isPositive,
                    label = "Diskon",
                    value = item.diskon.toString()
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "X"
                )
                ContentCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f),
                    isPositive = item.isPositive,
                    label = "Penjualan",
                    value = item.penjualan.toString()
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "X"
                )
                ContentCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f),
                    isPositive = item.isPositive,
                    label = if (item.isPositive) "Beli" else "Tidak Beli",
                    value = item.size.toString()
                )
            }
            Spacer(modifier = Modifier.padding(all = 8.dp))
            ContentCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                isPositive = item.isPositive,
                label = label,
                value = item.result.toString()
            )
        }
    }

}

@Composable
fun ContentHasilCalculate(
    modifier: Modifier,
    item: UiBuyRecommendation
) {
    Box(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(12.dp),
                color = BackgroundColor200
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "Hasil Rekomendasi"
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ContentCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f),
                    isPositive = item.positiveCalculate.isPositive,
                    label = "Class Beli",
                    value = item.positiveResult.toString()
                )
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    fontSize = 32.sp,
                    text = ">",
                    fontWeight = FontWeight.Bold
                )
                ContentCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f),
                    isPositive = item.negativeCalculate.isPositive,
                    label = "Class Tidak Beli",
                    value = item.negativeResult.toString()
                )
            }
            Spacer(modifier = Modifier.padding(all = 8.dp))
            ContentCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                isPositive = item.result,
                label = "Rekomendasi",
                value = item.recommendation.replace("Rekomendasi ", "")
            )
        }
    }
}

@Composable
fun ContentCard(
    modifier: Modifier,
    label: String = "Stok",
    isPositive: Boolean,
    value: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        CardEquation(
            modifier = Modifier.padding(top = 10.dp),
            isPositive = isPositive,
            value = value
        )
    }
}

@Composable
fun CardEquation(
    modifier: Modifier,
    isPositive: Boolean,
    value: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
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
                color = if (isPositive) Color.Green
                else Color.Red,
                thickness = 12.dp
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f, false),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = value,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

        }
    }
}

@Preview
@Composable
fun CardPreview() {
    ContentCard(modifier = Modifier, label = "Stok", isPositive = true, value = "0.5555")
}