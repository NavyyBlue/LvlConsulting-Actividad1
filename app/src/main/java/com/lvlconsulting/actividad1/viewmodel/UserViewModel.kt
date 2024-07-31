package com.lvlconsulting.actividad1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvlconsulting.actividad1.model.User
import com.lvlconsulting.actividad1.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun insertUser(user: User) {
        viewModelScope.launch {
            userRepository.insert(user)
        }
    }

    suspend fun getAllUsers() = userRepository.getAllUsers()
}