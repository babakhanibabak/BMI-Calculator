package com.example.bmicalculator.ui.BmiUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val isLoading by viewModel.isLoading.collectAsState()
    if (isLoading) {
        CircularProgressIndicator()
    } else {
        BmiScreenContent(
            onMaleClick = onMaleClick,
            onFemaleClick = onFemaleClick
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiScreenContent(
    onMaleClick: () -> Unit = {},
    onFemaleClick: () -> Unit = {},

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
                modifier = Modifier.fillMaxWidth(),
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
            Row(modifier = Modifier.padding(5.dp)) {
                OutlinedTextField(
                    modifier = Modifier.weight(0.5f),
                    value = age,
                    onValueChange = { age = it },
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
                    value = weight,
                    onValueChange = { weight = it },
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
                value = height,
                onValueChange = { height = it },
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
                        text = bmi.toString(),
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
                        text = idealWeight.toString(),
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
                        text = bodyFat.toString(),
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
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "BMI Classification",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontSize = 20.sp,
                color = Color.Black,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.size(30.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(modifier = Modifier.fillMaxWidth(), text = "Underweight:           BMI < 18.5")
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Normal weight:         18.5 <= BMI < 25"
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Overweight:            25 <= BMI < 30"
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "obese class I:         30 <= BMI < 35"
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "obese class II:        35 <= BMI < 40"
                )
                Text(modifier = Modifier.fillMaxWidth(), text = "obese class III:       BMI >= 40")
            }
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