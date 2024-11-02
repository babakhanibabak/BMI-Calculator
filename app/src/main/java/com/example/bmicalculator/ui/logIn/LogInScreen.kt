package com.example.bmicalculator.ui.logIn

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun LogInScreen(
    viewModel: LogInViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState by viewModel.uiState.collectAsState()
    if (uiState.isLoggedIn) {
        navController.navigate("bmi_screen") {
            popUpTo("login_screen") { inclusive = true }
        }
    }
    LogInScreenContent(
        uiState,
        onLoginClick = { viewModel.login(uiState.name, uiState.family) },
        onChangeUsername = viewModel::onChangeUsername,
    )
}


@Composable
fun LogInScreenContent(
    uiState: LoginState,
    onLoginClick: () -> Unit,
    onChangeUsername: (String) -> Unit,

) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = " Welcome! ", fontSize = 50.sp)
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        shape = RoundedCornerShape(25.dp),
                        color = Color.White
                    )
                    .padding(16.dp)
            ) {
                Text(text = " what's your name? ", fontSize = 25.sp)
                Spacer(modifier = Modifier.height(32.dp))
                TextFieldName(
                    uiState = uiState,
                    onChange = onChangeUsername
                )
                Spacer(modifier = Modifier.size(25.dp))
                Row {
                    LoginButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.1f),
                        onClick = onLoginClick
                    )
                }
            }
        }

    }
}


@Composable
fun TextFieldName(
    modifier: Modifier = Modifier,
    uiState: LoginState,
    onChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .border(color = Color.Black, width = 1.dp),
        value = uiState.name,
        onValueChange = onChange,
        shape = RoundedCornerShape(25.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Person, contentDescription = null)
        },
        placeholder = {
            Text(text = "name & family")
        }
    )
}

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(), onClick = onClick
    ) {
        Text(text = "Next ->")
    }
}

@Preview
@Composable
private fun LogInScreenContentPreview() {
    BMICalculatorTheme {
        LogInScreenContent(
            LoginState(),
            onLoginClick = {},
            onChangeUsername = {},
        )
    }
}