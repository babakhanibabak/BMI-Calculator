package com.example.bmicalculator.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.theme.normalColor

/***
 * @param value should be between 0 and 1
 * @param color color of the progress bar
 */
@Composable
fun CircularProgressBar(
    value: Float,
    color: Color,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = CircleShape,
            )
            .clip(CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            progress = { value },
            modifier = modifier,
            trackColor = Color.Gray.copy(alpha = 0.5f),
            color = color,
            strokeWidth = 10.dp,
            strokeCap = StrokeCap.Round
        )
        Text(
            text = value.toString(),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircularProgressBarPreview() {
    BMICalculatorTheme {
        CircularProgressBar(
            modifier = Modifier.size(100.dp),
            value = .3f,
            color = normalColor,
        )
    }
}