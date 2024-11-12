package com.example.bmicalculator.ui.bmi.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.bmicalculator.ui.navigation.BaseRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BmiResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val bmiResult = savedStateHandle.toRoute<BaseRoute.BmiScreen.BmiResult>()

}