package com.example.bmicalculator.ui.newuser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.ui.component.GenderCardUi
import com.example.bmicalculator.ui.navigation.BaseRoute
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.welcome.WelcomeScreenContent
import com.example.bmicalculator.ui.welcome.WelcomeScreenState

@Composable
fun RegisterNewUserScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        WelcomeScreenContent {
            NewUserScreenContent(
                uiState = WelcomeScreenState.NewUser(),
                onNextClick = {
                    navController.navigate(BaseRoute.Graph.Main)
                },
                onChangeUsername = {},
                onSelectGender = {},
            )
        }
    }
}

@Composable
private fun NewUserScreenContent(
    uiState: WelcomeScreenState.NewUser,
    onNextClick: () -> Unit,
    onChangeUsername: (String) -> Unit,
    onSelectGender: (Gender) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GenderCardUi(
                gender = Gender.MALE,
                isSelected = uiState.gender == Gender.MALE,
                onClick = { onSelectGender(Gender.MALE) }
            )
            Spacer(modifier = Modifier.size(16.dp))
            GenderCardUi(
                gender = Gender.FEMALE,
                isSelected = uiState.gender == Gender.FEMALE,
                onClick = { onSelectGender(Gender.FEMALE) }
            )
        }
        Spacer(modifier = Modifier.size(32.dp))
        Text(
            text = stringResource(R.string.what_is_your_full_name),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.fullName,
            onValueChange = onChangeUsername,
            isError = uiState.errorResId != null,
            supportingText = {
                uiState.errorResId?.let { Text(text = stringResource(it)) }
            },
            shape = RoundedCornerShape(25.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Person, contentDescription = null)
            },
            placeholder = {
                Text(text = stringResource(R.string.full_name))
            }
        )
        Spacer(modifier = Modifier.size(64.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = onNextClick,
                shape = RoundedCornerShape(15.dp)
            ) {
               Text(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = stringResource(R.string.next), fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenContentPreview() {
    BMICalculatorTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            NewUserScreenContent(
                uiState = WelcomeScreenState.NewUser(),
                onNextClick = {},
                onChangeUsername = {},
                onSelectGender = {}
            )
        }
    }
}