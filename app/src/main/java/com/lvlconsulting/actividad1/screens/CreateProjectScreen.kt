package com.lvlconsulting.actividad1.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.lvlconsulting.actividad1.R
import com.lvlconsulting.actividad1.components.CustomTextField
import com.lvlconsulting.actividad1.components.DatePickerField
import com.lvlconsulting.actividad1.model.Project
import com.lvlconsulting.actividad1.repository.ProjectRepository
import com.lvlconsulting.actividad1.ui.theme.BackgroundColor
import com.lvlconsulting.actividad1.ui.theme.BrandColor
import com.lvlconsulting.actividad1.ui.theme.LightGrayColor
import com.lvlconsulting.actividad1.ui.theme.SecondaryColor
import com.lvlconsulting.actividad1.ui.theme.TextColor
import com.lvlconsulting.actividad1.viewmodel.ProjectViewModel
import com.lvlconsulting.actividad1.viewmodel.ProjectViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProjectScreen(
    userId: Long,
    projectRepository: ProjectRepository,
    navController: NavHostController
) {

    val viewModel: ProjectViewModel = viewModel(factory = ProjectViewModelFactory(projectRepository))
    val context = LocalContext.current
    var projectName by remember { mutableStateOf("") }
    var projectDescription by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var shareWithOthers by remember { mutableStateOf(false) }

    var selectedStatus by remember { mutableStateOf("") }
    val statusOptions = listOf("PLANIFICACIÓN", "EN CURSO", "EN REVISIÓN", "FINALIZADO")

    var expanded by remember { mutableStateOf(false) }

    var selectedIcon by remember { mutableIntStateOf(R.drawable.icon_1) }
    var showIconDialog by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val iconOptions = listOf(
        R.drawable.icon_1,
        R.drawable.icon_2,
        R.drawable.icon_3,
        R.drawable.icon_4,
        R.drawable.icon_5,
        R.drawable.icon_6
    )

    val iconNames = listOf(
        "Folder",
        "Insurance",
        "Briefcase",
        "Advertising",
        "Email",
        "Calendar"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {
                    Text("Crear proyecto", color = TextColor)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            tint = BrandColor,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = "Icono del proyecto", fontSize = 14.sp, color = TextColor)
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .border(1.dp, LightGrayColor, shape = RoundedCornerShape(12.dp))
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .clickable { showIconDialog = true },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = selectedIcon),
                        contentDescription = "Project icon",
                        modifier = Modifier.size(30.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Cambiar icono aleatorio", fontSize = 14.sp, color = SecondaryColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    IconButton(onClick = {
                        selectedIcon = iconOptions.random()
                    }) {
                        Icon(
                            Icons.Filled.Autorenew,
                            tint = BrandColor,
                            contentDescription = "Random icon"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = projectName,
                onValueChange = { projectName = it },
                label = "Nombre del proyecto",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                value = projectDescription,
                onValueChange = { projectDescription = it },
                label = "Descripción",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                CustomTextField(
                    value = selectedStatus,
                    onValueChange = { selectedStatus = it },
                    label = "Estado del proyecto",
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Expand menu",
                            tint = BrandColor,
                            modifier = Modifier.clickable { expanded = !expanded }
                        )
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    statusOptions.forEach { status ->
                        DropdownMenuItem(
                            text = { Text(text = status) },
                            onClick = {
                                selectedStatus = status
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            DatePickerField(
                label = "Fecha de inicio",
                selectedDate = startDate,
                onDateSelected = { startDate = it }
            )

            Spacer(modifier = Modifier.height(10.dp))

            DatePickerField(
                label = "Fecha de finalización",
                selectedDate = endDate,
                onDateSelected = { endDate = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("¿Compartir con otros miembros?", color = TextColor, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = shareWithOthers,
                    onCheckedChange = { shareWithOthers = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = BrandColor,
                        checkedTrackColor = BrandColor.copy(alpha = 0.5f)
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.createProject(
                        userId = userId,
                        projectName = projectName,
                        projectDescription = projectDescription,
                        startDate = startDate,
                        endDate = endDate,
                        shareWithOthers = shareWithOthers,
                        selectedStatus = selectedStatus,
                        selectedIcon = selectedIcon,
                        onSuccess = {
                            navController.popBackStack()
                        },
                        onError = { errorMessage ->
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BrandColor)
            ) {
                Text(
                    "Crear proyecto", fontSize = 16.sp, color = Color.White,
                    modifier = Modifier.padding(15.dp)
                )
            }

            if (showIconDialog) {
                AlertDialog(
                    onDismissRequest = { showIconDialog = false },
                    title = { Text("Seleccionar icono", fontSize = 20.sp, color = TextColor) },
                    text = {
                        Column {
                            iconOptions.forEachIndexed { index, icon ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            selectedIcon = icon
                                            showIconDialog = false
                                        }
                                        .padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = icon),
                                        contentDescription = "Icon",
                                        modifier = Modifier.size(30.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = iconNames[index], color = TextColor)
                                }
                            }
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = { showIconDialog = false },
                            colors = ButtonDefaults.buttonColors(containerColor = BrandColor)
                        ) {
                            Text("Cerrar", color = Color.White)
                        }
                    },
                    containerColor = BackgroundColor
                )
            }


        }

    }
}

