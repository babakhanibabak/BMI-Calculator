package com.example.bmicalculator.ui.newuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.domain.model.UserModel
import com.example.bmicalculator.domain.usecase.InsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterNewUserViewModel @Inject constructor(
    private val insertUserUseCase: InsertUserUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterNewUserScreenState())
    val uiState by lazy {
        _uiState.asStateFlow()
    }

    private val _uiChannel = Channel<Long>()
    val uiChannel = _uiChannel.receiveAsFlow()

    fun onChangeUsername(name: String) {
        _uiState.update {
            it.copy(fullName = name, errorResId = null)
        }
    }

    fun onSelectGender(gender: Gender) {
        _uiState.update { it.copy(gender = gender) }
    }

    fun onNextClick() {
        if (_uiState.value.fullName.isEmpty()) {
            _uiState.update { it.copy(errorResId = R.string.full_name_empty_error) }
        } else {
            viewModelScope.launch {
                val userId = insertUserUseCase.execute(
                    user = UserModel(
                        fullName = _uiState.value.fullName,
                        gender = _uiState.value.gender,
                    )
                )
                _uiChannel.send(userId)
            }
        }
    }
}