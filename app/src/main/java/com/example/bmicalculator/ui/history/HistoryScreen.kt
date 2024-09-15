package com.example.bmicalculator.ui.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    HistoryScreenContent(uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HistoryScreenContent(
    uiState: HistoryScreenState,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(Color.LightGray),
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "BMI History",
                        textAlign = TextAlign.Center
                    )
                }
            )
        }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            when {
                uiState.isLoading -> Loading()
                uiState.isError -> Error()
                else -> ShowData(uiState.data)
            }
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
    ) {
        items(records) {
            HistoryItem(it)
        }
    }
}

@Composable
private fun HistoryItem(model: BmiHistoryUiModel) {
    Text("Bmi: ${model.bmi}")
}

@Preview
@Composable
private fun HistoryScreenContentPreview() {
    BMICalculatorTheme {
        HistoryScreenContent(HistoryScreenState())
    }
}