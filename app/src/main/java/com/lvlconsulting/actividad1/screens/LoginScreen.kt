package com.lvlconsulting.actividad1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lvlconsulting.actividad1.R
import com.lvlconsulting.actividad1.components.CustomTextField
import com.lvlconsulting.actividad1.ui.theme.BrandColor
import com.lvlconsulting.actividad1.ui.theme.SecondaryColor


@Composable
fun LoginScreenContent() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(true) }
    val logo = painterResource(id = R.drawable.logo)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = logo,
            contentDescription = "Logo",
            modifier = Modifier
                .height(106.dp)
                .width(80.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                fontSize = 22.sp,
            )
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.connector))
                    withStyle(style = SpanStyle(color = BrandColor)) {
                        append(stringResource(id = R.string.app_name))
                    }
                    append("!")
                },
                fontSize = 22.sp,
            )
            Text(
                text = stringResource(id = R.string.greetings_login),
                fontSize = 16.sp,
                color = SecondaryColor,
            )

            Spacer(modifier = Modifier.height(32.dp))

            CustomTextField(
                value = email,
                onValueChange = { email = it },
                label = stringResource(id =R.string.email),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                value = password,
                onValueChange = { password = it },
                label = stringResource(id = R.string.password),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisibility)
                        Icons.Default.Visibility
                    else
                        Icons.Default.VisibilityOff

                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            imageVector = image,
                            contentDescription = null,
                            tint = BrandColor
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = BrandColor,
                            uncheckedColor = SecondaryColor,
                            checkmarkColor = Color.White
                        )
                    )
                    Text(stringResource(id = R.string.remember_me), fontSize = 14.sp)
                }

                TextButton(onClick = { }) {
                    Text(stringResource(id = R.string.forgot_password), fontSize = 14.sp, color = BrandColor)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BrandColor)
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(15.dp)
                )
            }

        }

    }
}
