package me.skripsi.rekomendasibeliapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.skripsi.domain.ui_models.UiProductSelected

@Composable
fun CardProductSelected(
    modifier: Modifier = Modifier,
    productSelected: UiProductSelected,
    onClick: (checkedState: Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 10.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    text = "${productSelected.namaBarang}",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 6.dp),
                    text = "${productSelected.kategori}",
                    color = Color.Black.copy(alpha = 0.5f),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            }

            Checkbox(
                checked = productSelected.isSelected,
                onCheckedChange = { onClick.invoke(it) }
            )
        }
    }
}

@Preview
@Composable
fun CardProductPrev() {
    val dummy = UiProductSelected(
        kodeBarang = "101010",
        namaBarang = "Shofwan 1",
        kategori = "MAKANAN",
    )

    CardProductSelected(
        productSelected = dummy,
        onClick = {}
    )
}