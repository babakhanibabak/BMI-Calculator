package com.example.bmicalculator.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.domain.usecase.BmiRecordsFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val recordsFlowUseCase: BmiRecordsFlowUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryScreenState(isLoading = true))

    val uiState by lazy {
        initData()
        _uiState.asStateFlow()
    }

    private fun initData() {
        viewModelScope.launch {
            recordsFlowUseCase.execute(0).catch {
                _uiState.update { it.copy(isLoading = false, isError = true) }
            }.collect { records ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        data = records.map { record -> record.toUiModel() },
                    )
                }
            }
        }
    }
}