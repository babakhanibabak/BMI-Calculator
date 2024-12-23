package com.example.bmicalculator.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun OrLineUi(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), thickness = 2.dp)
        Text(text = stringResource(R.string.or))
        HorizontalDivider(modifier = Modifier.weight(1f), thickness = 2.dp)
    }
}


@Preview(showBackground = true)
@Composable
private fun OrLineUiContentPreview() {
    BMICalculatorTheme {
        OrLineUi()
    }
}