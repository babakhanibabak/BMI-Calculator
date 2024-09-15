package com.example.bmicalculator.ui.history

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun HistoryScreen() {
    HistoryScreenContent()
}


@Composable
fun HistoryScreenContent(
    modifier: Modifier = Modifier) {

}

@Preview
@Composable
private fun HistoryScreenContentPreview() {
    BMICalculatorTheme {
        HistoryScreenContent()
    }
}