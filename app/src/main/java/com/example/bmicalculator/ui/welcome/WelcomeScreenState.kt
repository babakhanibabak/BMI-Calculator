package com.example.bmicalculator.ui.welcome

import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.ui.common.model.UserUiModel

sealed class WelcomeScreenState {

    data object Loading : WelcomeScreenState()

    data class NewUser(
        val id: Long? = null,
        val fullName: String = "",
        val gender: Gender = Gender.MALE,
        val errorResId: Int? = null,
    ) : WelcomeScreenState()

    data class ExistingUsers(
        val users: List<UserUiModel>,
    ) : WelcomeScreenState()
}
