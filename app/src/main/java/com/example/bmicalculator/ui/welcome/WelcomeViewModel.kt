package com.example.bmicalculator.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<WelcomeScreenState> = MutableStateFlow(WelcomeScreenState.Loading)
    val uiState by lazy {
        initData()
        _uiState.asStateFlow()
    }

    private val _uiChannel = Channel<WelcomeScreenEvent>()
    val uiChannel = _uiChannel.receiveAsFlow()

    private fun initData() {
        viewModelScope.launch {
            runCatching {
                getAllUsersUseCase.execute()
            }.onSuccess {
                _uiState.value = WelcomeScreenState.Idle
                if (it.isEmpty()) {
                    _uiChannel.send(WelcomeScreenEvent.NavigateToNewUser)
                } else {
                    _uiChannel.send(WelcomeScreenEvent.NavigateToExistingUsers)
                }
            }.onFailure {
                _uiState.value = WelcomeScreenState.Error
            }
        }
    }
}
