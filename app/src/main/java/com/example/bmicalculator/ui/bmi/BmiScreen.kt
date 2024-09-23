package com.example.bmicalculator.ui.bmi

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun BmiScreen(
    viewModel: BmiViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    BmiScreenContent(
        uiState = uiState,
        onSelectGender = viewModel::onSelectGender,
        onWeightChange = viewModel::onWeightChange,
        onHeightChange = viewModel::onHeightChange,
        onAgeChange = viewModel::onAgeChange,
        onSaveToHistory = viewModel::onSaveToHistory
    )
}

@Composable
fun BmiScreenContent(
    uiState: BmiScreenState,
    onSelectGender: (Gender) -> Unit = {},
    onWeightChange: (String) -> Unit = {},
    onHeightChange: (String) -> Unit = {},
    onAgeChange: (String) -> Unit = {},
    onSaveToHistory: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "BMI Calculator",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Row(
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
        ) {
            Button(
                modifier = Modifier.weight(0.5f),
                onClick = {
                    onSelectGender(Gender.MALE)
                },
                shape = RectangleShape,
                colors = ButtonColors(
                    containerColor = if (uiState.gender == Gender.MALE) Color.Cyan else Color.White,
                    contentColor = Color.Gray,
                    disabledContentColor = Color.Cyan,
                    disabledContainerColor = Color.White
                )
            ) {
                Text(text = "Male")
            }
            Button(
                modifier = Modifier.weight(0.5f),
                onClick = { onSelectGender(Gender.FEMALE) },
                shape = RectangleShape,
                colors = ButtonColors(
                    containerColor = if (uiState.gender == Gender.FEMALE) Color.Cyan else Color.White,
                    contentColor = Color.Gray,
                    disabledContentColor = Color.Cyan,
                    disabledContainerColor = Color.White
                )
            ) {
                Text(text = "Female")
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        Row(modifier = Modifier.padding(5.dp)) {
            OutlinedTextField(
                modifier = Modifier.weight(0.5f),
                value = uiState.age,
                onValueChange = onAgeChange,
                label = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Age",
                        textAlign = TextAlign.Center
                    )
                })
            Spacer(modifier = Modifier.size(20.dp))
            OutlinedTextField(
                modifier = Modifier.weight(0.5f),
                value = uiState.weight,
                onValueChange = onWeightChange,
                label = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Weight (kg)",
                        textAlign = TextAlign.Center
                    )
                })
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(start = 30.dp, end = 30.dp),
            value = uiState.height,
            onValueChange = onHeightChange,
            label = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Height (cm)",
                    textAlign = TextAlign.Center
                )
            })

        Spacer(modifier = Modifier.size(10.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Black
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(0.33f)) {
                Text(
                    modifier = Modifier.fillMaxWidth(), text = "BMI", color = Color.Green,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(15.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = uiState.bmi.toString(),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
            Column(modifier = Modifier.weight(0.33f)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Ideal Weight",
                    color = Color.Blue,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(15.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = uiState.idealWeight.toString(),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
            Column(modifier = Modifier.weight(0.33f)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "FAT",
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(15.dp))
                Text(
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
            color = Color.Black
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
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
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            onClick = { onSaveToHistory() },
            colors = ButtonColors(
                containerColor = Color.Blue.copy(0.3f),
                contentColor = Color.Black,
                disabledContentColor = Color.Cyan,
                disabledContainerColor = Color.Cyan
            )
        ) {
            Text(text = "Save To History")
        }
    }
}

@Preview
@Composable
private fun BmiScreenContentPreview() {
    BMICalculatorTheme {
        BmiScreenContent(uiState = BmiScreenState())
    }
}