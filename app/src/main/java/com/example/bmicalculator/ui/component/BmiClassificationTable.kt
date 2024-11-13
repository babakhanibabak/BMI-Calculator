package com.example.bmicalculator.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.ui.bmi.BmiClassificationItem
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun BmiClassificationTable(
    bmiClassifications: List<BmiClassificationItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        bmiClassifications.forEach { item ->
            BmiClassificationItem(item = item)
        }
    }
}

@Composable
private fun BmiClassificationItem(
    item: BmiClassificationItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        BmiClassificationCircle(circleColor = item.bmiCircleColor)
        Text(
            modifier = Modifier
                .weight(1f)
                .background(
                    color = item.backgroundColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 4.dp),
            text = item.title,
            color = item.color,
            fontSize = 13.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BmiClassificationTablePreview(modifier: Modifier = Modifier) {
    BMICalculatorTheme {
        BmiClassificationTable(
            modifier = Modifier.padding(15.dp),
            bmiClassifications = listOf(
                BmiClassificationItem(
                    title = "Underweight",
                    color = Color.Black,
                    backgroundColor = Color.Green.copy(alpha = .5f),
                    bmiCircleColor = Color.Green,
                ),
                BmiClassificationItem(
                    title = "Normal",
                    color = Color.Black,
                    backgroundColor = Color.Blue.copy(alpha = .5f),
                    bmiCircleColor = Color.Blue,
                )
            )
        )
    }
}