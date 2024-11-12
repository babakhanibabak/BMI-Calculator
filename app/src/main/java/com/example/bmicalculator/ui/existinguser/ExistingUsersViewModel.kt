package com.example.bmicalculator.ui.existinguser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.domain.usecase.DeleteUserUseCase
import com.example.bmicalculator.domain.usecase.GetAllUsersUseCase
import com.example.bmicalculator.ui.common.model.UserUiModel
import com.example.bmicalculator.ui.common.model.toDomain
import com.example.bmicalculator.ui.common.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExistingUsersViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExistingUsersScreenState>(ExistingUsersScreenState.Loading)
    val uiState by lazy {
        initData()
        _uiState.asStateFlow()
    }

    private fun initData() {
        viewModelScope.launch {
            runCatching {
                getAllUsersUseCase.execute()
            }.onSuccess { userList ->
                _uiState.value = ExistingUsersScreenState.Success(
                    users = userList.map { it.toUiModel() }
                )
            }.onFailure { error ->
                _uiState.value = ExistingUsersScreenState.Error(error.message ?: "Unknown error")
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