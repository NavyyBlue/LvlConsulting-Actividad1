package com.lvlconsulting.actividad1.repository

import com.lvlconsulting.actividad1.database.ProjectDao
import com.lvlconsulting.actividad1.model.Project

class ProjectRepository(private val projectDao: ProjectDao) {
    suspend fun insert(project: Project) = projectDao.insert(project)
    suspend fun getProjectsByUserId(userId: Long) = projectDao.getProjectsForUser(userId)
}