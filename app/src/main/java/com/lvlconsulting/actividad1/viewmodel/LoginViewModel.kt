package com.lvlconsulting.actividad1.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lvlconsulting.actividad1.model.User
import com.lvlconsulting.actividad1.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _loginError = MutableStateFlow(false)
    val loginError: StateFlow<Boolean> = _loginError

    var loginResult: ((User?) -> Unit)? = null

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.getUserByEmailAndPassword(email, password)
            if (user == null) {
                _loginError.value = true
            } else {
                _loginError.value = false
                loginResult?.invoke(user)
            }
        }
    }
    fun resetLoginError() {
        _loginError.value = false
    }
}

class LoginViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}