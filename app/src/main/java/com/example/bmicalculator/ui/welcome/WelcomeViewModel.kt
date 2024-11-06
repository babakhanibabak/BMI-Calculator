package com.example.bmicalculator.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.domain.model.UserModel
import com.example.bmicalculator.domain.usecase.DeleteUserUseCase
import com.example.bmicalculator.domain.usecase.GetAllUsersUseCase
import com.example.bmicalculator.domain.usecase.InsertUserUseCase
import com.example.bmicalculator.ui.common.model.UserUiModel
import com.example.bmicalculator.ui.common.model.toDomain
import com.example.bmicalculator.ui.common.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<WelcomeScreenState> = MutableStateFlow(WelcomeScreenState.Loading)
    val uiState by lazy {
        initData()
        _uiState.asStateFlow()
    }

    private fun initData() {
        viewModelScope.launch {
            val users = getAllUsersUseCase.execute()
            if (users.isEmpty()) {
                _uiState.value = WelcomeScreenState.NewUser()
            } else {
                _uiState.value = WelcomeScreenState.ExistingUsers(
                    users = users.map { it.toUiModel() }
                )
            }
        }
    }

    fun onChangeUsername(name: String) {
        (_uiState.value as? WelcomeScreenState.NewUser)?.let { state ->
            _uiState.value = state.copy(fullName = name, errorResId = null)
        }
    }

    fun onSelectGender(gender: Gender) {
        (_uiState.value as? WelcomeScreenState.NewUser)?.let { state ->
            _uiState.value = state.copy(gender = gender)
        }
    }

    fun onNextClick() {
        (_uiState.value as? WelcomeScreenState.NewUser)?.let { state ->
            if (state.fullName.isEmpty()) {
                _uiState.value = state.copy(errorResId = R.string.full_name_empty_error)
            } else {
                viewModelScope.launch {
                    val userId = insertUserUseCase.execute(
                        user = UserModel(
                            fullName = state.fullName,
                            gender = state.gender,
                        )
                    )
                    _uiState.value = state.copy(id = userId)
                }
            }
        }
    }

    fun onDeleteUser(user: UserUiModel) {
        viewModelScope.launch {
            deleteUserUseCase.execute(user.toDomain())
            initData()
        }
    }
}
