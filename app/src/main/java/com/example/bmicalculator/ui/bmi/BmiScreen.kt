package com.example.bmicalculator.ui.bmi

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.ui.component.getCategoryColor
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
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiScreenContent(
    uiState: BmiScreenState,
    onSelectGender: (Gender) -> Unit = {},
    onWeightChange: (String) -> Unit = {},
    onHeightChange: (String) -> Unit = {},
    onAgeChange: (String) -> Unit = {},
) {
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
                    value = uiState.weight.toString(),
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
                value = uiState.height.toString(),
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
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.underweight),
                    color = getCategoryColor(category = stringResource(id = R.string.underweight))
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.normal_weight),
                    fontWeight = FontWeight.Bold,
                    color = getCategoryColor(category = stringResource(id = R.string.normal_weight))
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.overweight),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.obese_class_i),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.obese_class_ii),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(id = R.string.obese_class_iii),
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold
                )
            }
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