package com.lvlconsulting.actividad1.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.lvlconsulting.actividad1.ui.theme.BrandColor
import java.util.Calendar

@Composable
fun DatePickerField(
    label: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var date by remember { mutableStateOf(selectedDate) }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            onDateSelected(date)
        }, year, month, day
    )

    CustomTextField(
        value = date,
        onValueChange = { date = it },
        label = label,
        readOnly = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.CalendarToday,
                tint = BrandColor,
                contentDescription = "Select date",
                modifier = Modifier.clickable { datePickerDialog.show() }
            )
        },
        modifier = Modifier.clickable { datePickerDialog.show() }
    )
}