package com.example.bmicalculator.ui.bmi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.data.extensions.formatBmiValue
import com.example.bmicalculator.domain.model.BmiModel
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.domain.usecase.GetBmiUseCase
import com.example.bmicalculator.domain.usecase.GetBodyFatUseCase
import com.example.bmicalculator.domain.usecase.GetIdealWeightUseCase
import com.example.bmicalculator.domain.usecase.InsertBmiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val getBmiUseCase: GetBmiUseCase,
    private val getIdealWeightUseCase: GetIdealWeightUseCase,
    private val getBodyFatUseCase: GetBodyFatUseCase,
    private val insertBmiRecordUseCase: InsertBmiUseCase,
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
                        bmi = bmi.formatBmiValue(),
                        idealWeight = idealWeight.formatBmiValue(),
                        bodyFat = bodyFat.formatBmiValue(),
                    )
                }

                // Save to database
                viewModelScope.launch {
                    insertBmiRecordUseCase.execute(
                        record = BmiModel(
                            age = age,
                            gender = value.gender,
                            height = height,
                            weight = weight,
                            bmi = bmi,
                            idealWeight = idealWeight,
                            bodyFat = bodyFat,
                        )
                    )
                }
            }
        }
    }
}