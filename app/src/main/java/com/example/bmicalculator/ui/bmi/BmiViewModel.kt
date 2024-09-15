package com.example.bmicalculator.ui.bmi

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.domain.usecase.GetBmiUseCase
import com.example.bmicalculator.domain.usecase.GetBodyFatUseCase
import com.example.bmicalculator.domain.usecase.GetIdealWeightUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val getBmiUseCase: GetBmiUseCase,
    private val getIdealWeightUseCase: GetIdealWeightUseCase,
    private val getBodyFatUseCase: GetBodyFatUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(BmiScreenState())
    val uiState by lazy { _uiState.asStateFlow() }

    fun onSelectGender(gender: Gender) {
        _uiState.update { it.copy(gender = gender) }
        onCalculateBmi()
    }

    fun onWeightChange(weight: String) {
        _uiState.update { it.copy(weight = weight) }
        onCalculateBmi()
    }

    fun onHeightChange(height: String) {
        _uiState.update { it.copy(height = height) }
        onCalculateBmi()
    }

    fun onAgeChange(age: String) {
        _uiState.update { it.copy(age = age) }
        onCalculateBmi()
    }

    private fun onCalculateBmi() {
        with(uiState) {
            if (value.weight.isNotEmpty() && value.height.isNotEmpty() && value.age.isNotEmpty()) {
                val weight = value.weight.toDoubleOrNull() ?: 0.0
                val height = value.height.toDoubleOrNull() ?: 0.0
                val age = value.age.toIntOrNull() ?: 0
                val bmi = getBmiUseCase.execute(weight, height)
                val idealWeight = getIdealWeightUseCase.execute(height, value.gender)
                val bodyFat = getBodyFatUseCase.execute(bmi, age, value.gender)
                _uiState.update {
                    it.copy(
                        bmi = formatValue(bmi),
                        idealWeight = formatValue(idealWeight),
                        bodyFat = formatValue(bodyFat)
                    )
                }
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private fun formatValue(value: Double): String {
        return String.format("%.2f", value)
    }

    // TODO: Move this logic to HistoryScreen ================================

}