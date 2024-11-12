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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bmicalculator.ui.component.BmiClassificationCircle
import com.example.bmicalculator.ui.component.MyTextField
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.theme.Blue3
import com.example.bmicalculator.ui.theme.DarkBlue
import com.example.bmicalculator.ui.theme.DarkGreen

@Composable
fun BmiScreen(
    rootNavController: NavHostController,
    viewModel: BmiViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    BmiScreenContent(
        uiState = uiState,
        onNavigateUp = { rootNavController.navigateUp() },
        onWeightChange = viewModel::onWeightChange,
        onHeightChange = viewModel::onHeightChange,
        onAgeChange = viewModel::onAgeChange,
        onSaveToHistory = viewModel::onSaveToHistory
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiScreenContent(
    uiState: BmiScreenState,
    onNavigateUp: () -> Unit,
    onWeightChange: (String) -> Unit = {},
    onHeightChange: (String) -> Unit = {},
    onAgeChange: (String) -> Unit = {},
    onSaveToHistory: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBlue,
                ),
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                title = {
                    Text(text = "BMI Calculator", fontSize = 16.sp)
                },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
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
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Welcome ${uiState.fullName}",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column(
                modifier = Modifier
                    .padding(start = 25.dp, end = 25.dp, top = 5.dp, bottom = 10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MyTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.age,
                    onValueChange = onAgeChange,
                )
                MyTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.weight,
                    onValueChange = onWeightChange,
                )
                MyTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.height,
                    onValueChange = onHeightChange,
                )
            }

            Spacer(modifier = Modifier.size(10.dp))
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
                    Text(
                        modifier = Modifier.fillMaxWidth(), text = "BMI", color = DarkGreen,
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
                        color = DarkGreen,
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
                        color = DarkGreen,
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
                color = Color.LightGray
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            BmiClassificationCircle(circleColor = classification.BmiCircleColor)

                            Spacer(modifier = Modifier.width(8.dp))

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
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = { onSaveToHistory() },
                colors = ButtonColors(
                    containerColor = Blue3.copy(alpha = 0.4f),
                    contentColor = Color.Black,
                    disabledContentColor = Color.Cyan,
                    disabledContainerColor = Color.Cyan
                )
            ) {
                Text(text = "Save To History")
            }
        }
    }
}

@Preview
@Composable
private fun BmiScreenContentPreview() {
    BMICalculatorTheme {
        BmiScreenContent(uiState = BmiScreenState(), onNavigateUp = {})
    }
}