package com.lvlconsulting.actividad1.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvlconsulting.actividad1.model.User
import com.lvlconsulting.actividad1.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    var loginResult: ((User?) -> Unit)? = null

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.getUserByEmailAndPassword(email, password)
            loginResult?.invoke(user)
        }
    }
}