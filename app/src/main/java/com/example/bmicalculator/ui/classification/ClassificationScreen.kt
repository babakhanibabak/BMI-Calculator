package com.example.bmicalculator.ui.classification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
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
import com.example.bmicalculator.ui.theme.DarkGreen

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
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.LightGray
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(0.33f)) {
                androidx.compose.material3.Text(
                    modifier = Modifier.fillMaxWidth(), text = "BMI", color = DarkGreen,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(15.dp))
                androidx.compose.material3.Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = uiState.bmi.toString(),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
            Column(modifier = Modifier.weight(0.33f)) {
                androidx.compose.material3.Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Ideal Weight",
                    color = DarkGreen,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(15.dp))
                androidx.compose.material3.Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = uiState.idealWeight.toString(),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
            Column(modifier = Modifier.weight(0.33f)) {
                androidx.compose.material3.Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "FAT",
                    color = DarkGreen,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(15.dp))
                androidx.compose.material3.Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = uiState.bodyFat.toString(),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.LightGray
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            androidx.compose.material3.Text(
                text = "BMI Classification",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                fontSize = 20.sp,
                color = Color.Black,
                fontStyle = FontStyle.Italic
            )

            uiState.bmiClassifications?.let { classifications ->
                classifications.forEach { classification ->
                    androidx.compose.material3.Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                classification.backgroundColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(all = 8.dp),
                        text = classification.title,
                        fontWeight = FontWeight.Bold,
                        color = classification.color,
                    )
                }
            }
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