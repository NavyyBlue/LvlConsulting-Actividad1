package com.lvlconsulting.actividad1.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvlconsulting.actividad1.ui.theme.BrandColor
import com.lvlconsulting.actividad1.ui.theme.Geologica
import com.lvlconsulting.actividad1.ui.theme.SecondaryColor
import com.lvlconsulting.actividad1.ui.theme.TextColor

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    padding: Int? = null,
    readOnly: Boolean? = false,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(
                width = 1.dp,
                color = if (isFocused) BrandColor else SecondaryColor,
                shape = RoundedCornerShape(12.dp)
            )
            .background(Color.Transparent)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
    ) {
        if (value.isEmpty() && !isFocused) {
            Text(
                text = label,
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = padding?.dp ?: 16.dp)
            )
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                if (value.isNotEmpty() || isFocused) {
                    Text(
                        text = label,
                        color = if (isFocused) BrandColor else SecondaryColor,
                    )
                }
            },
            readOnly = readOnly ?: false,
            textStyle = TextStyle(
                fontSize = 16.sp, fontFamily = Geologica, color = TextColor
            ),
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = if (isFocused) BrandColor else SecondaryColor,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            )
        )
    }
}