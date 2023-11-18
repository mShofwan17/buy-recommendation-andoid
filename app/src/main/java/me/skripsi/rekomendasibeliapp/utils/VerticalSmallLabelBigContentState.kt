package me.skripsi.rekomendasibeliapp.utils

import androidx.compose.runtime.Composable

sealed class VerticalSmallLabelBigContentState {
    data class Text(val text: String) : VerticalSmallLabelBigContentState()
    data class Spinner(val composable: @Composable () -> Unit) : VerticalSmallLabelBigContentState()
    data class InputText(val composable: @Composable () -> Unit) : VerticalSmallLabelBigContentState()
}
