package com.example.bmicalculator.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun MyButton(
    text: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    suffixIcon: ImageVector? = null,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = text)
            suffixIcon?.let {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = it,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun MyButtonWithIconPreview() {
    BMICalculatorTheme {
        MyButton(
            text = "Next",
            onClick = {},
            suffixIcon = Icons.Default.PlayArrow
        )
    }
}

@Preview
@Composable
fun MyButtonWithoutIconPreview() {
    BMICalculatorTheme {
        MyButton(
            text = "Next",
            onClick = {},
            suffixIcon = null,
        )
    }
}