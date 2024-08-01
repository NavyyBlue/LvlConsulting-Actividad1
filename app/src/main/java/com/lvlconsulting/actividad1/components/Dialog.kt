package com.lvlconsulting.actividad1.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.lvlconsulting.actividad1.R
import com.lvlconsulting.actividad1.ui.theme.BrandColor
import com.lvlconsulting.actividad1.ui.theme.TextColor

@Composable
fun AdvancedSearchDialog(onDismiss: () -> Unit) {
    var projectCode by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var projectIcon by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(16.dp)
                .border(1.dp, Color.Transparent, RoundedCornerShape(12.dp))
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                item {
                    Text(
                        text = stringResource(id = R.string.search_advanced),
                        style = MaterialTheme.typography.titleSmall,
                        color = TextColor,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                item {
                    CustomTextField(
                        value = projectCode,
                        onValueChange = { projectCode = it },
                        label = stringResource(id = R.string.project_code)
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item {
                    CustomTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = stringResource(id = R.string.project_name)
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item {
                    CustomTextField(
                        value = status,
                        onValueChange = { status = it },
                        label = stringResource(id = R.string.project_status)
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item {
                    CustomTextField(
                        value = category,
                        onValueChange = { category = it },
                        label = stringResource(id = R.string.project_category)
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item {
                    CustomTextField(
                        value = projectIcon,
                        onValueChange = { projectIcon = it },
                        label = stringResource(id = R.string.project_icon)
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item {
                    DatePickerField(
                        label = stringResource(id = R.string.project_start_date),
                        onDateSelected = { startDate = it },
                        selectedDate = startDate
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item {
                    DatePickerField(
                        label = stringResource(id = R.string.project_end_date),
                        onDateSelected = { endDate = it },
                        selectedDate = endDate
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    CustomButton(
                        text = stringResource(id = R.string.search_hint),
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = BrandColor,
                        contentColor = Color.White
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    CustomButton(
                        text = stringResource(id = R.string.clean_hint),
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = Color.Transparent,
                        contentColor = BrandColor
                    )
                }
            }
        }
    }
}