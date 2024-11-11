package com.example.bmicalculator.ui.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.ui.common.model.UserUiModel
import com.example.bmicalculator.ui.component.GenderCardUi
import com.example.bmicalculator.ui.component.OrLineUi
import com.example.bmicalculator.ui.component.UserItem
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.theme.DarkBlue
import com.example.bmicalculator.ui.theme.DarkGreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    onNavigateToBmiScreen: (Long) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is WelcomeScreenState.Loading -> {
            WelcomeScreenContent {
                LoadingContent()
            }
        }

        is WelcomeScreenState.ExistingUsers -> {
            WelcomeScreenContent {
                ExistingUsersScreenContent(
                    uiState = uiState as WelcomeScreenState.ExistingUsers,
                    onDeleteUser = viewModel::onDeleteUser,
                    onNewUserClick = viewModel::onNewUserClick,
                    onUserSelect = { viewModel.onUserSelect(it.id) },
                )
            }
        }

        else -> Unit
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.uiChannel.collectLatest { userId ->
            onNavigateToBmiScreen(userId)
        }
    }
}

@Composable
fun WelcomeScreenContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxHeight / 2.5f)
                .background(brush = Brush.verticalGradient(colors = listOf(DarkGreen, DarkBlue)))
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier,
                text = stringResource(R.string.welcome),
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxHeight / 1.6f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .align(Alignment.BottomCenter)
                .padding(all = 32.dp),
        ) {
            content()
        }
    }
}

@Composable
private fun ExistingUsersScreenContent(
    uiState: WelcomeScreenState.ExistingUsers,
    onUserSelect: (UserUiModel) -> Unit = {},
    onDeleteUser: (UserUiModel) -> Unit = {},
    onNewUserClick: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = stringResource(R.string.continue_with_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        uiState.users.forEach {
            UserItem(
                model = it,
                onDelete = onDeleteUser,
                onClick = onUserSelect,
            )
        }
        OrLineUi(modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onNewUserClick,
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = stringResource(R.string.new_user), fontSize = 16.sp
            )
        }
    }
}

@Composable
fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun ExistingUsersScreenContentPreview() {
    BMICalculatorTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            ExistingUsersScreenContent(
                uiState = WelcomeScreenState.ExistingUsers(
                    users = listOf(
                        UserUiModel(id = 1, fullName = "John", gender = Gender.MALE),
                        UserUiModel(id = 1, fullName = "John", gender = Gender.MALE),
                    )
                ),
            )
        }
    }
}