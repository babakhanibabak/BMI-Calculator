package com.example.bmicalculator.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.data.datasource.database.dao.BmiDao
import com.example.bmicalculator.data.datasource.database.entity.BmiCalculationResult
import com.example.bmicalculator.data.datasource.database.entity.BmiEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BmiViewModel @Inject constructor(
    private val bmiDao: BmiDao
) : ViewModel() {

    val bmiEntity: BmiEntity = bmiDao.getAllBmi()
    fun calculateBmi(age: Int, height: Int, weight: Int): BmiCalculationResult {
        val heightInMeters = height.toFloat() / 100
        val bmi = weight.toFloat() / (heightInMeters * heightInMeters)
        val bmiCategory = when {
            bmi < 18.5 -> "Underweight"
            bmi in 18.5..24.9 -> "Normal weight"
            bmi in 25.0..29.9 -> "Overweight"
            bmi in 30.0..34.9 -> " obese class I"
            bmi in 35.0..39.9 -> "obese class II"
            else -> "obese class III"
        }
        return BmiCalculationResult(bmi, bmiCategory)
    }

    suspend fun insertBmiData(bmiEntity: BmiEntity) {
        bmiDao.insertBmiData(bmiEntity)
    }

    fun deleteAllBmiData() {
        viewModelScope.launch {
            bmiDao.deleteBmiCalc(id = 0)
        }
    }

}