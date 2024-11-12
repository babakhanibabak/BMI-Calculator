package com.example.bmicalculator.ui.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.common.model.UserUiModel
import com.example.bmicalculator.ui.component.MainGradientBackgroundContent
import com.example.bmicalculator.ui.component.MyButton
import com.example.bmicalculator.ui.component.MyLoading
import com.example.bmicalculator.ui.navigation.BaseRoute

@Composable
fun MainHomeScreen(
    rootNavController: NavHostController,
    mainData: BaseRoute.Graph.Main,
    viewModel: MainHomeViewModel = hiltViewModel<MainHomeViewModel>().apply {
        setUserId(mainData.userId)
    },
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MainHomeScreenContent(
        uiState = uiState,
        onCalculateClick = { userModel ->
            rootNavController.navigate(BaseRoute.BmiScreen.Bmi(userId = userModel.id))
        },
    )
}

@Composable
private fun MainHomeScreenContent(
    uiState: MainHomeScreenState,
    onCalculateClick: (UserUiModel) -> Unit,
) {
    MainGradientBackgroundContent(
        title = uiState.title,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            when (uiState) {
                is MainHomeScreenState.Loading -> MyLoading()
                is MainHomeScreenState.Error -> Text(text = uiState.message)
                is MainHomeScreenState.Success -> ShowContent(uiState = uiState, onCalculateClick = onCalculateClick)
            }
        }
    }
}

@Composable
private fun ShowContent(
    uiState: MainHomeScreenState.Success,
    onCalculateClick: (UserUiModel) -> Unit,
) {
    when {
        uiState.data.bmiData.isNullOrEmpty() -> EmptyContent(
            uiState = uiState,
            onCalculateClick = onCalculateClick,
        )

        else -> BmiRecordsList()
    }
}

@Composable
private fun EmptyContent(
    uiState: MainHomeScreenState.Success,
    onCalculateClick: (UserUiModel) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            imageVector = Icons.Filled.DateRange,
            contentDescription = null,
            tint = Color.LightGray,
        )
        Text(text = stringResource(id = R.string.no_bmi_data_found))
        MyButton(text = stringResource(R.string.calculate)) {
            uiState.data.userData?.let {
                onCalculateClick(it)
            }
        }
    }
}

@Composable
private fun BmiRecordsList() {
    // TODO
}