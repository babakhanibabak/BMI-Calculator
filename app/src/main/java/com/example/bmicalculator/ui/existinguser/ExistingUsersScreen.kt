package com.example.bmicalculator.ui.existinguser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.common.model.UserUiModel
import com.example.bmicalculator.ui.component.GradientBackgroundContent
import com.example.bmicalculator.ui.component.MyLoading
import com.example.bmicalculator.ui.component.OrLineUi
import com.example.bmicalculator.ui.component.UserItem
import com.example.bmicalculator.ui.navigation.BaseRoute

@Composable
fun ExistingUsersScreen(
    navController: NavHostController,
    viewModel: ExistingUsersViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ExistingUsersScreenContent(
        uiState = uiState,
        onDeleteUser = viewModel::onDeleteUser,
        onNewUserClick = {
            navController.navigate(BaseRoute.RegistrationScreen.NewUser)
        },
        onUserSelect = { userModel ->
            navController.navigate(BaseRoute.Graph.Main(userId = userModel.id)) {
                popUpTo(BaseRoute.RegistrationScreen.ExistingUser) {
                    inclusive = true
                }
            }
        },
    )
}

@Composable
private fun ExistingUsersScreenContent(
    uiState: ExistingUsersScreenState,
    onUserSelect: (UserUiModel) -> Unit = {},
    onDeleteUser: (UserUiModel) -> Unit = {},
    onNewUserClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        GradientBackgroundContent {
            when(uiState) {
                is ExistingUsersScreenState.Loading -> {
                    MyLoading()
                }
                is ExistingUsersScreenState.Error -> {
                    Text(text = uiState.message)
                }
                is ExistingUsersScreenState.Success -> {
                    UsersListContent(
                        uiState = uiState,
                        onUserSelect = onUserSelect,
                        onDeleteUser = onDeleteUser,
                        onNewUserClick = onNewUserClick,
                    )
                }
            }
        }
    }
}

@Composable
fun UsersListContent(
    uiState: ExistingUsersScreenState.Success,
    modifier: Modifier = Modifier,
    onUserSelect: (UserUiModel) -> Unit = {},
    onDeleteUser: (UserUiModel) -> Unit = {},
    onNewUserClick: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        if (uiState.users.isNotEmpty()) {
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
        }
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
