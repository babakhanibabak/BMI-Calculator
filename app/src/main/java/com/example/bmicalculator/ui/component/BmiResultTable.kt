package com.example.bmicalculator.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.theme.LightBlue2

@SuppressLint("DefaultLocale")
@Composable
fun BmiResultTable(
    age: Int,
    height: Double,
    weight: Double,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = LightBlue2.copy(alpha = 0.3f),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TableColumn(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.age), value = age.toString()
        )
        VerticalDivider(modifier = Modifier.heightIn(max = 40.dp))
        TableColumn(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.weight), value = String.format("%.2f", weight)
        )
        VerticalDivider(modifier = Modifier.heightIn(max = 40.dp))
        TableColumn(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.height), value = String.format("%.2f", height)
        )
    }
}

@Composable
fun TableColumn(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = title)
        Text(text = value)
    }
}

@Preview(showBackground = true)
@Composable
fun BmiResultTablePreview() {
    BMICalculatorTheme {
        BmiResultTable(
            age = 25,
            weight = 70.0,
            height = 20.0
        )
    }
}