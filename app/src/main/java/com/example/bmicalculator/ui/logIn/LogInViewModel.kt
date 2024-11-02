package com.example.bmicalculator.ui.logIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmicalculator.data.datasource.database.dao.AccountDao
import com.example.bmicalculator.data.datasource.database.dao.BmiDao
import com.example.bmicalculator.domain.model.BmiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
   private val accountDao: AccountDao,
    private val bmiDao: BmiDao
):ViewModel() {

    private val _logInState = MutableStateFlow(LoginState())
     val uiState by lazy {
        _logInState.asStateFlow()
    }
   // private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
   // val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private val _bmiRecords = MutableStateFlow<List<BmiModel>>(emptyList())
    val bmiRecords: StateFlow<List<BmiModel>> = _bmiRecords.asStateFlow()

//    sealed class LoginState {
//        object Idle : LoginState()
//        object Success : LoginState()
//        data class Error(val message: String) : LoginState()
//    }

    fun onChangeUsername(value: String) {
        _logInState.update {
            it.copy(name = value)
        }
    }

    fun onChangePassword(value: String) {
        _logInState.update {
            it.copy(family = value)
        }
    }

    fun onShowPassword() {
        _logInState.update {
            it.copy(showFamily   = !it.showFamily)
        }
    }

    fun login(name:String,family:String){
        viewModelScope.launch {
            val account=accountDao.getAccountByUsernameAndPassword(username =name, password = family)
            if (account !=null){
                _logInState.update { it.copy(isLoggedIn = true, errorMessage = null) }
//                _logInState.value= LoginState()
                loadUserBmiRecords(account.accountId)
            } else {
                _logInState.update { it.copy(errorMessage = "Invalid credentials", isLoggedIn = false) }
            }
        }
    }

   private fun loadUserBmiRecords(accountId:Int){
        viewModelScope.launch {
            bmiDao.getBmiByAccount(accountId).map { it }
            _logInState.value = uiState.value
        }}
    }
