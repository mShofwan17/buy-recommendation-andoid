package me.skripsi.rekomendasibeliapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.skripsi.rekomendasibeliapp.ui.theme.BackgroundColorSecondary
import me.skripsi.rekomendasibeliapp.utils.VerticalSmallLabelBigContentState
import me.skripsi.rekomendasibeliapp.utils.viewDiskonLabel

@Composable
fun SideBySideText(
    modifier: Modifier = Modifier,
    textLabel: @Composable () -> Unit,
    textContent: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
    ) {
        textLabel()
        Text(
            modifier = Modifier.padding(start = 6.dp, end = 10.dp),
            text = ":"
        )
        textContent()
    }
}

@Composable
fun VerticalSmallLabelBigContentLayout(
    modifier: Modifier = Modifier,
    textLabel: String,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: VerticalSmallLabelBigContentState
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            modifier = Modifier.padding(bottom = 2.dp),
            text = textLabel,
            color = Color.Black.copy(alpha = 0.5f),
            fontSize = MaterialTheme.typography.labelSmall.fontSize
        )
        when (content) {
            is VerticalSmallLabelBigContentState.Text -> {
                Text(
                    modifier = Modifier,
                    text = content.text,
                    color = Color.Black,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
            }

            is VerticalSmallLabelBigContentState.Spinner -> content.composable()
            is VerticalSmallLabelBigContentState.InputText -> content.composable()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyInputText(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .height(46.dp)
            .background(BackgroundColorSecondary)
            .clip(
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        TextField(
            modifier = Modifier.fillMaxSize(),
            value = text,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                val textChanged = it.ifEmpty { "0" }
                onTextChange(textChanged)
            },
            textStyle = TextStyle(
                color = Color.Black
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = BackgroundColorSecondary,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun FormCheckBox(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: (Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .height(46.dp)
            .background(BackgroundColorSecondary)
            .clip(
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp, end = 2.dp),
                text = isSelected.viewDiskonLabel(),
                fontSize = MaterialTheme.typography.labelLarge.fontSize
            )
            Checkbox(
                checked = isSelected,
                onCheckedChange = { onClick.invoke(it) }
            )
        }
    }

}

@Composable
fun LoadingContent(
    modifier: Modifier = Modifier,
    labelLoading : String
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(bottom = 10.dp),
            color = Color.Green
        )
        Text(text = labelLoading)
    }
}

@Preview(showBackground = true)
@Composable
private fun VerticalSmallLabelBigContentTextPrev() {
    VerticalSmallLabelBigContentLayout(
        textLabel = "Nama Barang",
        content = VerticalSmallLabelBigContentState.Text("Chil Go Steril Coklat 140ml")
    )
}

@Preview(showBackground = true)
@Composable
private fun MyInputTextPrev() {
    MyInputText(
        modifier = Modifier.fillMaxWidth(),
        text = "",
        onTextChange = {
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun FormCheckBoxPrev() {
    FormCheckBox(
        modifier = Modifier.fillMaxWidth(),
        isSelected = true,
        onClick = {
        }
    )
}