package com.lvlconsulting.actividad1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val companyName: String,
    val jobTitle: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val photoUri: String
)