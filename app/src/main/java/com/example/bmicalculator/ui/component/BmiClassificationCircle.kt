package com.example.bmicalculator.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun BmiClassificationCircle(circleColor: Color) {
    Box(
        modifier = Modifier
            .size(12.dp)
            .background(circleColor, shape = CircleShape)
    )
}
