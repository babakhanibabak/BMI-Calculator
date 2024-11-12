package com.example.bmicalculator.ui.main.home

import com.example.bmicalculator.ui.common.model.UserUiModel
import com.example.bmicalculator.ui.history.BmiHistoryUiModel

sealed class MainHomeScreenState(open val title: String) {
    data object Loading : MainHomeScreenState(title = "")
    data class Error(val message: String) : MainHomeScreenState(title = "")
    data class Success(
        override val title: String = "",
        val data: UserBmiDataUiModel,
    ) : MainHomeScreenState(title)
}

data class UserBmiDataUiModel(
    val userData: UserUiModel? = null,
    val bmiData: List<BmiHistoryUiModel>? = null,
)