package me.skripsi.rekomendasibeliapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.skripsi.rekomendasibeliapp.R

@Composable
fun CardHome(
    modifier: Modifier = Modifier,
    title: String,
    image: Painter,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .height(130.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .background(Color.Blue),
        contentAlignment = Alignment.BottomStart,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = image,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.image_home)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )
        Text(
            modifier = Modifier.padding(
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )


    }
}

@Preview
@Composable
fun Preview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
    ) {
        CardHome(
            title = stringResource(R.string.data_transaksi),
            image = painterResource(id = R.drawable.transaction)
        ) {

        }
        Spacer(modifier = Modifier.padding(10.dp))
        CardHome(
            title = stringResource(R.string.data_tranining),
            image = painterResource(id = R.drawable.training)
        ) {}
    }

}