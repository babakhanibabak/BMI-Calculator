package com.example.bmicalculator.ui.bmi.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.component.BmiClassificationTable
import com.example.bmicalculator.ui.component.BmiResultTable
import com.example.bmicalculator.ui.component.BmiCircularProgressBar
import com.example.bmicalculator.ui.component.MainGradientBackgroundContent
import com.example.bmicalculator.ui.component.MyLoading
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.theme.normalColor

@Composable
fun BmiResultScreen(
    rootNavController: NavHostController,
    viewModel: BmiResultViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BmiResultScreenContent(
        uiState = uiState,
    )
}

@Composable
private fun BmiResultScreenContent(
    uiState: BmiResultScreenState,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        MainGradientBackgroundContent(
            modifier = Modifier.padding(paddingValues),
            title = stringResource(R.string.your_bmi_result)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                when (uiState) {
                    is BmiResultScreenState.Loading -> MyLoading()
                    is BmiResultScreenState.Error -> Text(text = uiState.message)
                    is BmiResultScreenState.Success -> BmiResultDisplayContent(state = uiState)
                }
            }
        }
    }
}

@Composable
private fun BmiResultDisplayContent(
    state: BmiResultScreenState.Success,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        BmiCircularProgressBar(
            modifier = Modifier.size(96.dp),
            bmiValue = state.bmiValue,
            formattedBmiValue = state.formattedBmiValue.orEmpty(),
            color = normalColor,
        )
        Text(
            text = "You have Normal Body Weight",
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
        BmiResultTable(
            age = state.age,
            weight = state.weight,
            height = state.height,
        )
        BmiClassificationTable(
            bmiClassifications = state.bmiClassifications.orEmpty()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BmiResultScreenContentPreview() {
    BMICalculatorTheme {
        BmiResultScreenContent(
            uiState = BmiResultScreenState.Success()
        )
    }
}
