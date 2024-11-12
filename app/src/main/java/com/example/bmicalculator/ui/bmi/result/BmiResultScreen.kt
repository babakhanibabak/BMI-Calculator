package com.example.bmicalculator.ui.bmi.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.bmi.calculate.BmiClassificationItem
import com.example.bmicalculator.ui.component.BmiClassificationTable
import com.example.bmicalculator.ui.component.BmiResultTable
import com.example.bmicalculator.ui.component.CircularProgressBar
import com.example.bmicalculator.ui.component.MainGradientBackgroundContent
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.theme.normalColor

@Composable
fun BmiResultScreen(
    rootNavController: NavHostController,
    viewModel: BmiResultViewModel = hiltViewModel(),
) {
    BmiResultScreenContent()
}

@Composable
private fun BmiResultScreenContent() {
    MainGradientBackgroundContent(
        title = stringResource(R.string.your_bmi_result)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            CircularProgressBar(
                modifier = Modifier.size(96.dp),
                value = .5f,
                color = normalColor,
            )
            Text(
                text = "You have Normal Body Weight",
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )
            BmiResultTable(
                age = 25,
                weight = 70.0,
                height = 20.0
            )
            BmiClassificationTable(
                bmiClassifications = listOf(
                    BmiClassificationItem(
                        title = "Underweight",
                        color = Color.Black,
                        backgroundColor = Color.Green.copy(alpha = .5f),
                        bmiCircleColor = Color.Green,
                    ),
                    BmiClassificationItem(
                        title = "Normal",
                        color = Color.Black,
                        backgroundColor = Color.Blue.copy(alpha = .5f),
                        bmiCircleColor = Color.Blue,
                    )
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BmiResultScreenContentPreview() {
    BMICalculatorTheme {
        BmiResultScreenContent()
    }
}
