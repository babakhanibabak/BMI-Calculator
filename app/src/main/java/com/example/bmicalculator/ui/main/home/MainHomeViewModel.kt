package com.example.bmicalculator.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.domain.usecase.BmiRecordsFlowUseCase
import com.example.bmicalculator.domain.usecase.UserByIdFlowUseCase
import com.example.bmicalculator.ui.common.model.toUiModel
import com.example.bmicalculator.ui.history.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainHomeViewModel @Inject constructor(
    private val userByIdFlowUseCase: UserByIdFlowUseCase,
    private val bmiRecordsFlowUseCase: BmiRecordsFlowUseCase,
) : ViewModel() {

    private var userId: Long = 0L

    fun setUserId(userId: Long) {
        this.userId = userId
    }

    private val _uiState = MutableStateFlow<MainHomeScreenState>(MainHomeScreenState.Loading)
    val uiState by lazy {
        initData()
        _uiState.asStateFlow()
    }

    private fun initData() {
        viewModelScope.launch {
            combine(
                flow = userByIdFlowUseCase.execute(userId),
                flow2 = bmiRecordsFlowUseCase.execute(userId),
            ) { user, records ->
                UserBmiDataUiModel(
                    userData = user?.toUiModel(),
                    bmiData = records.map { it.toUiModel() }
                )
            }.catch {
                _uiState.value = MainHomeScreenState.Error(it.message ?: "Unknown error")
            }.collect { data ->
                _uiState.value = MainHomeScreenState.Success(
                    title = "Welcome ${data.userData?.fullName}",
                    data = data,
                )
            }
        }
    }
}