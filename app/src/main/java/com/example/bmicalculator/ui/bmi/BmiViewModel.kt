package com.example.bmicalculator.ui.bmi

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.data.datasource.database.entity.BmiEntity
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.domain.repository.BmiRepository
import com.example.bmicalculator.domain.usecase.GetBmiUseCase
import com.example.bmicalculator.domain.usecase.GetBodyFatUseCase
import com.example.bmicalculator.domain.usecase.GetIdealWeightUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val repository: BmiRepository,
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

    private val _bmiRecords = MutableStateFlow<List<BmiEntity>>(emptyList())
    val bmiRecords: StateFlow<List<BmiEntity>> = _bmiRecords.asStateFlow()

    fun calculateBmi(record: BmiEntity) {
        viewModelScope.launch {
            try {
                val heightInMeters = record.height / 100
                val bmi = record.weight / (heightInMeters * heightInMeters)
                val bmiCategory = when {
                    bmi < 18.5 -> "Underweight"
                    bmi in 18.5..24.9 -> "Normal weight"
                    bmi in 25.0..29.9 -> "Overweight"
                    bmi in 30.0..34.9 -> " obese class I"
                    bmi in 35.0..39.9 -> "obese class II"
                    else -> "obese class III"
                }
                insertBmiData(record)
            } finally {
            }

        }
    }

    fun insertBmiData(record: BmiEntity) {
        viewModelScope.launch {
            repository.insert(record)
            loadRecords()
        }
    }

    private fun loadRecords() {
        viewModelScope.launch {
            repository.getAllRecords().collect { records ->
                _bmiRecords.value = records
            }
        }
    }
}