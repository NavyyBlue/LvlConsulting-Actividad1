package com.lvlconsulting.actividad1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lvlconsulting.actividad1.model.Project

@Dao
interface ProjectDao {
    @Insert
    suspend fun insert(project: Project): Long

    @Query("SELECT * FROM Project WHERE userId = :userId")
    suspend fun getProjectsForUser(userId: Long): List<Project>
}