package com.lvlconsulting.actividad1.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.lvlconsulting.actividad1.R
import com.lvlconsulting.actividad1.components.AdvancedSearchDialog
import com.lvlconsulting.actividad1.components.BoardCard
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

    remember { projectViewModel.loadProjectsByUserId(userId) }

    var searchQuery by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val filteredProjects = projects.filter { it.name.contains(searchQuery, ignoreCase = true) }

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
                            modifier = Modifier.clickable(onClick = { navController.navigate("profile/${userId}") })
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profile),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            val firstWordOfFirstName = firstName.split(" ").firstOrNull() ?: ""
                            val firstWordOfLastName = lastName.split(" ").firstOrNull() ?: ""

                            Column {
                                Text(
                                    "$firstWordOfFirstName $firstWordOfLastName",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = TextColor,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    jobTitle,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = SecondaryColor
                                )
                            }
                        }
                        IconButton(onClick = { }) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(48.dp)
                                    .border(1.dp, LightGrayColor, CircleShape)
                            ) {
                                Icon(
                                    Icons.Filled.Notifications,
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
                Icon(Icons.Default.Add, tint = Color.White, contentDescription = null)
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
                    label = stringResource(id = R.string.search_label),
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
                            contentDescription = null,
                            modifier = Modifier.clickable { showDialog = true }
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    stringResource(id = R.string.table_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = TextColor,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(filteredProjects) { project ->
                        BoardCard(
                            iconResId = project.iconResId,
                            title = project.name,
                            code = project.description,
                            status = project.status,
                            statusColor = when (project.status) {
                                stringResource(id = R.string.planning_status) -> PlanningStatusColor
                                stringResource(id = R.string.in_progress_status) -> InProgressStatusColor
                                stringResource(id = R.string.in_review_status) -> InReviewStatusColor
                                stringResource(id = R.string.completed_status) -> CompletedStatusColor
                                else -> Color.Gray
                            }
                        )
                    }
                }
            }
            if (showDialog) {
                AdvancedSearchDialog(onDismiss = { showDialog = false })
            }
        }
    )
}

