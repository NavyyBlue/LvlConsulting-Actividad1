package com.lvlconsulting.actividad1.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.lvlconsulting.actividad1.R
import com.lvlconsulting.actividad1.components.CustomTextField
import com.lvlconsulting.actividad1.repository.ProjectRepository
import com.lvlconsulting.actividad1.ui.theme.BrandColor
import com.lvlconsulting.actividad1.ui.theme.CompletedStatusColor
import com.lvlconsulting.actividad1.ui.theme.InProgressStatusColor
import com.lvlconsulting.actividad1.ui.theme.InReviewStatusColor
import com.lvlconsulting.actividad1.ui.theme.LightGrayColor
import com.lvlconsulting.actividad1.ui.theme.PlanningStatusColor
import com.lvlconsulting.actividad1.ui.theme.SecondaryColor
import com.lvlconsulting.actividad1.ui.theme.TextColor
import com.lvlconsulting.actividad1.viewmodel.ProjectViewModel
import com.lvlconsulting.actividad1.viewmodel.ProjectViewModelFactory

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    firstName: String,
    lastName: String,
    jobTitle: String,
    userId: Long,
    projectRepository: ProjectRepository,
    navController: NavHostController
) {
    val projectViewModel: ProjectViewModel =
        viewModel(factory = ProjectViewModelFactory(projectRepository))
    val projects by projectViewModel.projects.collectAsState()

    // Load projects for the logged-in user
    remember { projectViewModel.loadProjectsByUserId(userId) }

    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                ),
                modifier = Modifier.padding(top = 16.dp, end = 16.dp),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable(onClick = { navController.navigate("profile") })
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profile), // Reemplaza con tu recurso de imagen
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    "$firstName $lastName",
                                    fontSize = 22.sp,
                                    color = TextColor,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(jobTitle, fontSize = 14.sp, color = SecondaryColor)
                            }
                        }
                        IconButton(onClick = { /* Acción de notificación */ }) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(48.dp)
                                    .border(1.dp, LightGrayColor, CircleShape)
                            ) {
                                Icon(
                                    Icons.Default.Notifications,
                                    tint = BrandColor,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(56.dp),
                onClick = {
                    navController.navigate("create_project/${userId}")
                },
                containerColor = BrandColor
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = "Buscar en tableros",
                    padding = 48,
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            tint = BrandColor,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Tune,
                            tint = BrandColor,
                            contentDescription = null
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Boards Section
                Text("Tableros", fontSize = 20.sp, color = TextColor, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                println("Projects: $projects")
                // Boards Grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(projects) { project ->
                        BoardCard(
                            iconResId = project.iconResId,
                            title = project.name,
                            code = project.description,
                            status = project.status,
                            statusColor = when (project.status) {
                                "PLANIFICACIÓN" -> PlanningStatusColor
                                "EN CURSO" -> InProgressStatusColor
                                "EN REVISIÓN" -> InReviewStatusColor
                                "FINALIZADO" -> CompletedStatusColor
                                else -> Color.Gray
                            }
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun BoardCard(
    iconResId: Int,
    title: String,
    code: String,
    status: String,
    statusColor: Color
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(code, fontSize = 14.sp, color = Color.Gray, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = status,
                color = TextColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(statusColor, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}