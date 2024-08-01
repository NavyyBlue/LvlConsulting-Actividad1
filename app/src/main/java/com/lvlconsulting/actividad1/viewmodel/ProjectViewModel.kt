package com.lvlconsulting.actividad1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lvlconsulting.actividad1.model.Project
import com.lvlconsulting.actividad1.repository.ProjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProjectViewModel(private val projectRepository: ProjectRepository) : ViewModel() {
    private val _projects = MutableStateFlow<List<Project>>(emptyList())
    val projects: StateFlow<List<Project>> = _projects

    fun loadProjectsByUserId(userId: Long) {
        viewModelScope.launch {
            _projects.value = projectRepository.getProjectsByUserId(userId)
        }
    }

    fun createProject(
        userId: Long,
        projectName: String,
        projectDescription: String,
        startDate: String,
        endDate: String,
        shareWithOthers: Boolean,
        selectedStatus: String,
        selectedIcon: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (projectName.isBlank()
            || projectDescription.isBlank()
            || startDate.isBlank()
            || endDate.isBlank()
            || selectedStatus.isBlank()
        ) {
            onError("Se requieren todos los campos")
            return
        }

        val dateFormat = SimpleDateFormat("d/M/yyyy", Locale.getDefault())

        try {
            val parsedStartDate =
                dateFormat.parse(startDate) ?: throw ParseException("Invalid start date", 0)
            val parsedEndDate =
                dateFormat.parse(endDate) ?: throw ParseException("Invalid end date", 0)

            val newProject = Project(
                userId = userId,
                name = projectName,
                description = projectDescription,
                status = selectedStatus,
                startDate = parsedStartDate,
                endDate = parsedEndDate,
                shareWithOthers = shareWithOthers,
                iconResId = selectedIcon
            )

            viewModelScope.launch {
                try {
                    projectRepository.insert(newProject)
                    onSuccess()
                } catch (e: Exception) {
                    onError(e.message ?: "Unknown error")
                }
            }
        } catch (e: ParseException) {
            onError("Invalid date format: ${e.message}")
        }
    }
}

class ProjectViewModelFactory(private val projectRepository: ProjectRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProjectViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProjectViewModel(projectRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}