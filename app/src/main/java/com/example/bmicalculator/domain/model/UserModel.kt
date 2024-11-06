package com.example.bmicalculator.domain.model

data class UserModel(
    val id: Long = 0,
    val fullName: String,
    val gender: Gender,
)
