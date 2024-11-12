package com.example.bmicalculator.ui.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text

@Composable
fun MainHomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToBmi: () -> Unit = {},
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Text(
                "Main Home Screen",
                color = Color.Red
            )

            Button(onClick = {
                onNavigateToBmi()
            }) {
                Text("Go to BMI Screen")
            }
        }
    }
}