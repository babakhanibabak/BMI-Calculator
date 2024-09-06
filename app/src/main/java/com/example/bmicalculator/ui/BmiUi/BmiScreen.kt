package com.example.bmicalculator.ui.BmiUi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun BmiScreen(
    viewModel: BmiViewModel = hiltViewModel(),
    onMaleClick: () -> Unit={},
    onFemaleClick:()-> Unit={}
) {
    BmiScreenContent(
        onMaleClick = onMaleClick,
        onFemaleClick = onFemaleClick
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiScreenContent(
                     onMaleClick: () -> Unit={},
                     onFemaleClick:()-> Unit={}
                     ) {
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
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .padding(paddingValues)) {

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