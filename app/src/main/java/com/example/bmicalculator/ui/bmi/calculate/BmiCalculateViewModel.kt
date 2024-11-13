package com.example.bmicalculator.ui.bmi.calculate

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.bmicalculator.domain.usecase.GetBmiUseCase
import com.example.bmicalculator.domain.usecase.GetBodyFatUseCase
import com.example.bmicalculator.domain.usecase.GetIdealWeightUseCase
import com.example.bmicalculator.domain.usecase.GetUserByIdUseCase
import com.example.bmicalculator.ui.navigation.BaseRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BmiCalculateViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getBmiUseCase: GetBmiUseCase,
    private val getIdealWeightUseCase: GetIdealWeightUseCase,
    private val getBodyFatUseCase: GetBodyFatUseCase,
    @ApplicationContext private val context: Context,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val userId = savedStateHandle.toRoute<BaseRoute.BmiScreen.Bmi>().userId

    private val _uiState = MutableStateFlow(BmiCalculateScreenState())
    val uiState by lazy {
        initData()
        _uiState.asStateFlow()
    }

    private val _uiChannel = Channel<BaseRoute.BmiScreen.BmiResult>()
    val uiChannel = _uiChannel.receiveAsFlow()

    private fun initData() {
        viewModelScope.launch {
            userId.let { id ->
                getUserByIdUseCase.execute(id)?.let { userModel ->
                    _uiState.update {
                        it.copy(
                            userId = userModel.id,
                            fullName = userModel.fullName,
                            gender = userModel.gender,
                        )
                    }
                }
            }
        }
    }

    fun onWeightChange(weight: String) {
        _uiState.update { it.copy(weight = weight) }
    }

    fun onHeightChange(height: String) {
        _uiState.update { it.copy(height = height) }
    }

    fun onAgeChange(age: String) {
        _uiState.update { it.copy(age = age) }
    }

    fun onCalculateBmi() {
        viewModelScope.launch {
            with(_uiState.value) {
                if (weight.isNotEmpty() && height.isNotEmpty() && age.isNotEmpty()) {
                    val weightValue = weight.toDoubleOrNull() ?: 0.0
                    val heightValue = height.toDoubleOrNull() ?: 0.0
                    val ageValue = age.toIntOrNull() ?: 0

                    val bmi = getBmiUseCase.execute(weightValue, heightValue)
                    val idealWeight = getIdealWeightUseCase.execute(heightValue, gender)
                    val bodyFat = getBodyFatUseCase.execute(bmi, ageValue, gender)

                    _uiChannel.send(
                        BaseRoute.BmiScreen.BmiResult(
                            age = ageValue,
                            height = heightValue,
                            weight = weightValue,
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