package com.lvlconsulting.actividad1.repository

import com.lvlconsulting.actividad1.database.UserDao
import com.lvlconsulting.actividad1.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun getUserByEmailAndPassword(email: String, password: String) =
        userDao.getUserByEmailAndPassword(email, password)

    suspend fun getUserById(userId: Long) = userDao.getUserById(userId)
}