package com.example.bmicalculator.ui.classification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Text
import com.example.bmicalculator.ui.bmi.BmiScreenState
import com.example.bmicalculator.ui.bmi.BmiViewModel
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun ClassificationScreen(
    viewModel: BmiViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

   ClassificationScreenContent(uiState)
}


@Composable
fun ClassificationScreenContent(
    uiState: BmiScreenState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp),
            text = "BMI Classification",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,)

        uiState.bmiClassifications?.let {
        }
    }
}

@Preview
@Composable
private fun ClassificationScreenContentPreview() {
    BMICalculatorTheme {
        ClassificationScreenContent(uiState = BmiScreenState())
    }
}