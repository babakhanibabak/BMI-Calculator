package com.example.bmicalculator.ui.bmi.calculate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.ui.component.MyButton
import com.example.bmicalculator.ui.component.MyTextField
import com.example.bmicalculator.ui.component.UserInfoContainer
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.theme.DarkBlue
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BmiScreen(
    rootNavController: NavHostController,
    viewModel: BmiCalculateViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    BmiScreenContent(
        uiState = uiState,
        onNavigateUp = { rootNavController.navigateUp() },
        onWeightChange = viewModel::onWeightChange,
        onHeightChange = viewModel::onHeightChange,
        onAgeChange = viewModel::onAgeChange,
        onCalculateBmi = viewModel::onCalculateBmi,
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.uiChannel.collectLatest { bmiResultRoute ->
            rootNavController.navigate(bmiResultRoute)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiScreenContent(
    uiState: BmiCalculateScreenState,
    onNavigateUp: () -> Unit,
    onWeightChange: (String) -> Unit = {},
    onHeightChange: (String) -> Unit = {},
    onAgeChange: (String) -> Unit = {},
    onCalculateBmi: () -> Unit = {},
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
                title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            UserInfoContainer(
                fullName = uiState.fullName,
                gender = uiState.gender,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MyTextField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(R.string.enter_your_age),
                    value = uiState.age,
                    onValueChange = onAgeChange,
                )
                MyTextField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(R.string.enter_your_wight),
                    value = uiState.weight,
                    onValueChange = onWeightChange,
                )
                MyTextField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(R.string.enter_your_height),
                    value = uiState.height,
                    onValueChange = onHeightChange,
                )
                Spacer(modifier = Modifier.size(32.dp))
                MyButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.calculate_your_bmi),
                ) {
                    onCalculateBmi()
                }
            }
        }
    }
}

@Preview
@Composable
private fun BmiScreenContentPreview() {
    BMICalculatorTheme {
        BmiScreenContent(uiState = BmiCalculateScreenState(
            fullName = "John Doe",
            gender = Gender.MALE,
            age = "25",
            weight = "70",
            height = "170",
        ), onNavigateUp = {})
    }
}