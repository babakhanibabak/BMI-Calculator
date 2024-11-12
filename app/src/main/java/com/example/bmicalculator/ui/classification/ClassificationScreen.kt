package com.example.bmicalculator.ui.classification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bmicalculator.ui.bmi.calculate.BmiCalculateScreenState
import com.example.bmicalculator.ui.bmi.calculate.BmiCalculateViewModel
import com.example.bmicalculator.ui.component.CircularProgressBar
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.theme.DarkBlue
import com.example.bmicalculator.ui.theme.DarkGreen
import com.example.bmicalculator.ui.theme.LightBlue2
import com.example.bmicalculator.ui.theme.normalColor

@Composable
fun ClassificationScreen(
    viewModel: BmiCalculateViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ClassificationScreenContent(uiState)
}


@Composable
fun ClassificationScreenContent(
    uiState: BmiCalculateScreenState
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .height(maxHeight / 5f)
                .background(
                    brush = Brush.verticalGradient(colors = listOf(DarkGreen, DarkBlue)),
                    shape = RoundedCornerShape(25.dp)
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = "Your BMI Result",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, bottom = 16.dp),
                fontSize = 20.sp,
                color = Color.Black,
                fontStyle = FontStyle.Italic,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.8f)
                    .background(color = Color.White, shape = RoundedCornerShape(25.dp))
            ) {
                CircularProgressBar(
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp),
                    value = 0.3f,
                    color = normalColor )
                Text(
                    textAlign = TextAlign.Center,
                    text = "You have Normal Body Weight",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp),
                    fontSize = 20.sp,
                    color = Color.Black)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    uiState.bmiClassifications?.let { classifications ->
                        classifications.forEach { classification ->
                            Text(
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
    }
}


@Preview
@Composable
private fun ClassificationScreenContentPreview() {
    BMICalculatorTheme {
        ClassificationScreenContent(uiState = BmiCalculateScreenState())
    }
}