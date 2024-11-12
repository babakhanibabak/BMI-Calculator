package com.example.bmicalculator.ui.newuser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.ui.component.GenderCardUi
import com.example.bmicalculator.ui.component.GradientBackgroundContent
import com.example.bmicalculator.ui.component.MyButton
import com.example.bmicalculator.ui.navigation.BaseRoute
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterNewUserScreen(
    navController: NavHostController,
    viewModel: RegisterNewUserViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NewUserScreenContent(
        uiState = uiState,
        onSelectGender = viewModel::onSelectGender,
        onChangeUsername = viewModel::onChangeUsername,
        onNextClick = viewModel::onNextClick,
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.uiChannel.collectLatest { userId ->
            navController.navigate(BaseRoute.Graph.Main(userId = userId)) {
                popUpTo(BaseRoute.Graph.Registration) {
                    inclusive = true
                }
            }
        }
    }
}

@Composable
private fun NewUserScreenContent(
    uiState: RegisterNewUserScreenState,
    onNextClick: () -> Unit,
    onChangeUsername: (String) -> Unit,
    onSelectGender: (Gender) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        GradientBackgroundContent {
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
                    MyButton(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        contentPadding = PaddingValues(horizontal = 32.dp),
                        text = stringResource(R.string.next),
                        suffixIcon = Icons.Filled.PlayArrow,
                        onClick = onNextClick,
                    )
                }
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
                uiState = RegisterNewUserScreenState(),
                onNextClick = {},
                onChangeUsername = {},
                onSelectGender = {}
            )
        }
    }
}
