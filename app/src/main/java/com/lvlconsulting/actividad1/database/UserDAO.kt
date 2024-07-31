package com.lvlconsulting.actividad1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lvlconsulting.actividad1.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): User?
}