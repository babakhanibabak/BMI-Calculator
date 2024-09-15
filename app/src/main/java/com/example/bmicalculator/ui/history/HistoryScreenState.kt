package com.example.bmicalculator.ui.history

data class HistoryScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: List<BmiHistoryUiModel> = emptyList(),
)