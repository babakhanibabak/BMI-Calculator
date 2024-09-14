package com.example.bmicalculator.ui.bmi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.data.datasource.database.entity.BmiEntity
import com.example.bmicalculator.domain.repository.BmiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val repository: BmiRepository,
    // private val bmiDao: BmiDao
) : ViewModel() {

    private val _bmiRecords = MutableStateFlow<List<BmiEntity>>(emptyList())
    val bmiRecords: StateFlow<List<BmiEntity>> = _bmiRecords.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadRecords()
    }

    //val bmiEntity: BmiEntity = bmiDao.getAllBmi()
    fun calculateBmi(record: BmiEntity) {
        viewModelScope.launch {
            _isLoading.value = true
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
            }finally {
                _isLoading.value=false
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
            repository.getAllRecords().collect {
                records-> _bmiRecords.value=records
            }
        }
    }
}