package com.example.bmicalculator.ui.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.component.MyLoading
import com.example.bmicalculator.ui.navigation.BaseRoute
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    viewModel: WelcomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is WelcomeScreenState.Loading -> {
            LoadingContent()
        }

        is WelcomeScreenState.Error -> {
            Text(text = stringResource(R.string.unknown_error_occurred))
        }

        else -> Unit
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.uiChannel.collectLatest { event ->
            when (event) {
                is WelcomeScreenEvent.NavigateToNewUser -> {
                    navController.navigateToRegistration(BaseRoute.RegistrationScreen.NewUser)
                }

                is WelcomeScreenEvent.NavigateToExistingUsers -> {
                    navController.navigateToRegistration(BaseRoute.RegistrationScreen.ExistingUser)
                }
            }
        }
    }
}

private fun NavHostController.navigateToRegistration(route: BaseRoute.RegistrationScreen) {
    navigate(route) {
        popUpTo(BaseRoute.WelcomeScreen) {
            inclusive = true
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        MyLoading()
    }
}
