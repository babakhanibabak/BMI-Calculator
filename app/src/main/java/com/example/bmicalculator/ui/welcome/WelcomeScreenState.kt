package com.example.bmicalculator.ui.welcome

sealed interface WelcomeScreenState {
    data object Loading : WelcomeScreenState
    data object Error : WelcomeScreenState
    data object Idle : WelcomeScreenState
}

sealed interface WelcomeScreenEvent {
    data object NavigateToNewUser : WelcomeScreenEvent
    data object NavigateToExistingUsers : WelcomeScreenEvent
}
