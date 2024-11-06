package com.example.bmicalculator.ui.common.model

import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.domain.model.UserModel

data class UserUiModel(
    val id: Long,
    val fullName: String,
    val gender: Gender,
)

fun UserModel.toUiModel() = UserUiModel(
    id = id,
    fullName = fullName,
    gender = gender,
)

fun UserUiModel.toDomain() = UserModel(
    id = id,
    fullName = fullName,
    gender = gender,
)