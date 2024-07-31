package com.lvlconsulting.actividad1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val name: String,
    val iconResId: Int,
    val description: String,
    val status: String,
    val startDate: Date,
    val endDate: Date,
    val shareWithOthers: Boolean
)