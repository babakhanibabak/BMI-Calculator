package com.example.bmicalculator.ui.BmiUi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bmicalculator.ui.component.calculateBmi
import com.example.bmicalculator.ui.component.calculateBodyFat
import com.example.bmicalculator.ui.component.calculateIdealWeight
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun BmiScreen(
    viewModel: BmiViewModel = hiltViewModel(),
    onMaleClick: () -> Unit = {},
    onFemaleClick: () -> Unit = {}
) {
    BmiScreenContent(
        onMaleClick = onMaleClick,
        onFemaleClick = onFemaleClick
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiScreenContent(
    onMaleClick: () -> Unit = {},
    onFemaleClick: () -> Unit = {}
) {
    var height by remember {
        mutableStateOf("")
    }
    var weight by remember {
        mutableStateOf("")
    }
    var age by remember {
        mutableStateOf("")
    }
    val gender by remember {
        mutableStateOf("")
    }
    val bmi = calculateBmi(height.toFloatOrNull(), weight.toFloatOrNull())
    val idealWeight = calculateIdealWeight(height.toFloatOrNull(), gender)
    val bodyFat = calculateBodyFat(bmi, age.toIntOrNull(), gender)

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier,
                colors = TopAppBarDefaults.topAppBarColors(Color.LightGray),
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Bmi Calculator",
                        textAlign = TextAlign.Center
                    )
                }
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)

            ) {
                Button(
                    modifier = Modifier.weight(0.5f),
                    onClick = onMaleClick, shape = RectangleShape,
                    colors = ButtonColors(
                        containerColor = Color.Cyan,
                        contentColor = Color.Gray,
                        disabledContentColor = Color.Cyan,
                        disabledContainerColor = Color.White
                    )
                ) {
                    Text(text = "Male")

                }
                Button(
                    modifier = Modifier.weight(0.5f),
                    onClick = onFemaleClick,
                    shape = RectangleShape
                ) {
                    Text(text = "Female")
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
           Row(modifier = Modifier.padding(5.dp)){
               OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text(text = "Age") })
               OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text(text = "Weight (kg)") })
        }
            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text(text = "Height (cm)") })
        }

    }
}


@Preview
@Composable
private fun BmiScreenContentPreview() {
    BMICalculatorTheme {
        BmiScreenContent()
    }
}