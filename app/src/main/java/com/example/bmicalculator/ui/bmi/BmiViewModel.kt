package com.example.bmicalculator.ui.bmi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.data.extensions.formatBmiValue
import com.example.bmicalculator.domain.model.BmiModel
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.domain.usecase.BmiRecordsFlowUseCase
import com.example.bmicalculator.domain.usecase.GetBmiUseCase
import com.example.bmicalculator.domain.usecase.GetBodyFatUseCase
import com.example.bmicalculator.domain.usecase.GetIdealWeightUseCase
import com.example.bmicalculator.domain.usecase.InsertBmiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val getBmiUseCase: GetBmiUseCase,
    private val getIdealWeightUseCase: GetIdealWeightUseCase,
    private val getBodyFatUseCase: GetBodyFatUseCase,
    private val insertBmiRecordUseCase: InsertBmiUseCase
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
    // Save BMI, Ideal Weight, and Body Fat to history
    fun onSaveToHistory() {
        with(_uiState) {
            if (value.weight.isNotEmpty() && value.height.isNotEmpty() && value.age.isNotEmpty()) {
                val weightValue = value.weight.toDoubleOrNull() ?: 0.0
                val heightValue = value.height.toDoubleOrNull() ?: 0.0
                val ageValue = value.age.toIntOrNull() ?: 0

                // Calculate BMI, ideal weight, and body fat
                val bmi = getBmiUseCase.execute(weightValue, heightValue)
                val idealWeight = getIdealWeightUseCase.execute(heightValue, value.gender)
                val bodyFat = getBodyFatUseCase.execute(bmi, ageValue,value.gender)

                // Update UI state with the results
                _uiState.update {
                    it.copy(
                        bmi = bmi.formatBmiValue(),
                        idealWeight = idealWeight.formatBmiValue(),
                        bodyFat = bodyFat.formatBmiValue()
                    )
                }

                // Save to the database
                viewModelScope.launch {
                    insertBmiRecordUseCase.execute(
                        record = BmiModel(
                            age = ageValue,
                            gender = value.gender,
                            height = heightValue,
                            weight = weightValue,
                            bmi = bmi,
                            idealWeight = idealWeight,
                            bodyFat = bodyFat
                        )
                    )
                }

            }
        }
    }

    private fun onCalculateBmi() {
        with(_uiState.value) {
            if (weight.isNotEmpty() && height.isNotEmpty() && age.isNotEmpty()) {
                val weightValue = weight.toDoubleOrNull() ?: 0.0
                val heightValue = height.toDoubleOrNull() ?: 0.0
                val ageValue = age.toIntOrNull() ?: 0

                val bmi = getBmiUseCase.execute(weightValue, heightValue)
                val idealWeight = getIdealWeightUseCase.execute(heightValue, gender)
                val bodyFat = getBodyFatUseCase.execute(bmi, ageValue, gender)

                _uiState.update {
                    it.copy(
                        bmi = bmi.formatBmiValue(),
                        idealWeight = idealWeight.formatBmiValue(),
                        bodyFat = bodyFat.formatBmiValue()
                    )
                }
            }
        }
    }
}