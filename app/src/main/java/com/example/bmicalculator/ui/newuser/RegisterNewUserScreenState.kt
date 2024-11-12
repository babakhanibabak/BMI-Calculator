package com.example.bmicalculator.ui.newuser

import com.example.bmicalculator.domain.model.Gender

data class RegisterNewUserScreenState(
    val id: Long? = null,
    val fullName: String = "",
    val gender: Gender = Gender.MALE,
    val errorResId: Int? = null,
)