package com.example.bmicalculator.ui.existinguser

import com.example.bmicalculator.ui.common.model.UserUiModel

sealed class ExistingUsersScreenState {
    data object Loading : ExistingUsersScreenState()
    data class Error(val message: String) : ExistingUsersScreenState()
    data class Success(val users: List<UserUiModel>) : ExistingUsersScreenState()
}