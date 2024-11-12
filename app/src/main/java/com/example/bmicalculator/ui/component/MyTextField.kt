package com.example.bmicalculator.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.theme.DarkBlue
import com.example.bmicalculator.ui.theme.MyBlue

@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
) {
    TextField(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MyBlue,
            unfocusedContainerColor = MyBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray,
        ),
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        isError = isError,
        supportingText = supportingText,
        leadingIcon = leadingIcon,
        placeholder = placeholder?.let {
            { Text(text = it, color = DarkBlue) }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyTextFieldPreview() {
    BMICalculatorTheme {
        Column(Modifier.padding(16.dp)) {
            MyTextField(
                value = "",
                placeholder = "Enter your age",
                onValueChange = {},
            )
        }
    }
}