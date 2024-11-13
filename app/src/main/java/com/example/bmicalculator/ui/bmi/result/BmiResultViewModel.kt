package com.example.bmicalculator.ui.bmi.result

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.bmicalculator.data.extensions.formatBmiValue
import com.example.bmicalculator.domain.usecase.InsertBmiUseCase
import com.example.bmicalculator.ui.bmi.BmiClassificationMapper
import com.example.bmicalculator.ui.navigation.BaseRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BmiResultViewModel @Inject constructor(
    private val insertBmiRecordUseCase: InsertBmiUseCase,
    @ApplicationContext private val context: Context,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val bmiResult = savedStateHandle.toRoute<BaseRoute.BmiScreen.BmiResult>()

    private val _uiState = MutableStateFlow<BmiResultScreenState>(BmiResultScreenState.Loading)
    val uiState by lazy {
        initData()
        _uiState.asStateFlow()
    }

    private fun initData() {
        if (bmiResult.bmiRecordId != null) {
            // TODO : Get Data from Database
        } else {
            _uiState.value = BmiResultScreenState.Success(
                weight = bmiResult.weight.formatBmiValue(),
                height = bmiResult.height.formatBmiValue(),
                age = bmiResult.age.toString(),
                bmiValue = bmiResult.bmi,
                formattedBmiValue = bmiResult.bmi.formatBmiValue(),
                idealWeight = bmiResult.idealWeight.formatBmiValue(),
                bodyFat = bmiResult.bodyFat.formatBmiValue(),
                bmiClassifications = BmiClassificationMapper.mapClassifications(
                    bmi = bmiResult.bmi.formatBmiValue(),
                    context = context,
                )
            )
        }
    }

    // Save BMI, Ideal Weight, and Body Fat to history
    /*fun onSaveToHistory() {
        with(_uiState) {
            if (value.weight.isNotEmpty() && value.height.isNotEmpty() && value.age.isNotEmpty()) {
                val weightValue = value.weight.toDoubleOrNull() ?: 0.0
                val heightValue = value.height.toDoubleOrNull() ?: 0.0
                val ageValue = value.age.toIntOrNull() ?: 0

                // Calculate BMI, ideal weight, and body fat
                val bmi = getBmiUseCase.execute(weightValue, heightValue)
                val idealWeight = getIdealWeightUseCase.execute(heightValue, value.gender)
                val bodyFat = getBodyFatUseCase.execute(bmi, ageValue, value.gender)

                // Save to the database
                viewModelScope.launch {
                    insertBmiRecordUseCase.execute(
                        record = BmiModel(
                            userId = value.userId,
                            age = ageValue,
                            height = heightValue,
                            weight = weightValue,
                            bmi = bmi,
                            idealWeight = idealWeight,
                            bodyFat = bodyFat,
                            date = java.time.LocalDateTime.now(),
                        )
                    )
                }
            }
        }
    }*/
}