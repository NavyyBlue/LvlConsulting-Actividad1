package com.lvlconsulting.actividad1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.lvlconsulting.actividad1.database.AppDatabase
import com.lvlconsulting.actividad1.model.Project
import com.lvlconsulting.actividad1.model.User
import com.lvlconsulting.actividad1.repository.ProjectRepository
import com.lvlconsulting.actividad1.repository.UserRepository
import com.lvlconsulting.actividad1.screens.CreateProjectScreen
import com.lvlconsulting.actividad1.screens.EditPhotoScreen
import com.lvlconsulting.actividad1.screens.HomeScreen
import com.lvlconsulting.actividad1.screens.LoginScreen
import com.lvlconsulting.actividad1.screens.ProfileScreen
import com.lvlconsulting.actividad1.ui.theme.Actividad1Theme
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : ComponentActivity() {
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Actividad1)
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "users-db"
        ).build()
        val userDao = db.userDao()
        val projectDao = db.projectDao()
        val userRepository = UserRepository(userDao)
        val projectRepository = ProjectRepository(projectDao)
        enableEdgeToEdge()
        setContent {
            Actividad1Theme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(navController, userRepository, projectRepository)
                }
            }
        }
        lifecycleScope.launch {
            if (userDao.getAllUsers().isEmpty()) {
                val userId = userDao.insert(
                    User(
                        firstName = "Miguel Ángel",
                        lastName = "Liberato Carmín",
                        companyName = "LVL Consulting",
                        jobTitle = "CEO LVL Consulting",
                        phoneNumber = "+51 987654321",
                        email = "miguelliberato@gmail.com",
                        password = "1234",
                        photoUri = "path/to/photo"
                    )
                )

                projectDao.insert(
                    Project(
                        userId = userId,
                        name = "Project 1",
                        description = "Description of Project 1",
                        status = "EN CURSO",
                        startDate = Date(),
                        endDate = Date(),
                        shareWithOthers = true,
                        iconResId = R.drawable.icon_6
                    )
                )

                projectDao.insert(
                    Project(
                        userId = userId,
                        name = "Project 2",
                        description = "Description of Project 2",
                        status = "FINALIZADO",
                        startDate = Date(),
                        endDate = Date(),
                        shareWithOthers = true,
                        iconResId = R.drawable.icon_1
                    )
                )
                projectDao.insert(
                    Project(
                        userId = 2,
                        name = "Project 2",
                        description = "Description of Project 2",
                        status = "EN REVISIÓN",
                        startDate = Date(),
                        endDate = Date(),
                        shareWithOthers = true,
                        iconResId = R.drawable.icon_2
                    )
                )
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    userRepository: UserRepository,
    projectRepository: ProjectRepository
) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(userRepository, navController)
        }
        composable("home/{firstName}/{lastName}/{jobTitle}/{userId}") { backStackEntry ->
            val firstName = backStackEntry.arguments?.getString("firstName") ?: ""
            val lastName = backStackEntry.arguments?.getString("lastName") ?: ""
            val jobTitle = backStackEntry.arguments?.getString("jobTitle") ?: ""
            val userId = backStackEntry.arguments?.getString("userId")?.toLong() ?: 0L
            HomeScreen(firstName, lastName, jobTitle, userId, projectRepository, navController)
        }
        composable("create_project/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toLong() ?: 0L
            CreateProjectScreen(userId, projectRepository, navController)
        }
        composable("profile/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toLong() ?: 0L
            ProfileScreen(navController, userId, userRepository)
        }
        composable("edit_profile") {
            EditPhotoScreen()
        }
    }
}