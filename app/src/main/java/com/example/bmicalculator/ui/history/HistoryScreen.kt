package com.example.bmicalculator.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    HistoryScreenContent(uiState)
}

@Composable
private fun HistoryScreenContent(
    uiState: HistoryScreenState,
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(all = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        when {
            uiState.isLoading -> Loading()
            uiState.isError -> Error()
            else -> ShowData(uiState.data)
        }
    }
}

@Composable
private fun Loading() {
    CircularProgressIndicator()
}

@Composable
private fun Error() {
    Text("Error Occurred")
}

@Composable
private fun ShowData(records: List<BmiHistoryUiModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(records) {
            HistoryItem(it)
        }
    }
}

@Composable
private fun HistoryItem(model: BmiHistoryUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(model.backGroundColor, shape = RoundedCornerShape(16.dp)).padding(16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text("BMI: ${model.bmi}")
        Text("Ideal Weight: ${model.idealWeight}")
        Text("Body Fat: ${model.bodyFat}")
    }
}

@Preview
@Composable
private fun HistoryScreenContentPreview() {
    BMICalculatorTheme {
        HistoryScreenContent(HistoryScreenState())
    }
}