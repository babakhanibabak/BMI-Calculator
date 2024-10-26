package com.example.bmicalculator.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.theme.GenderSelectedColor

@Composable
fun GenderCardUi(
    gender: Gender,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .clickable { onClick() },
        colors = CardColors(
            contentColor = Color.Transparent,
            disabledContentColor = Color.Transparent,
            containerColor = if (isSelected) GenderSelectedColor else Color.White,
            disabledContainerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(gender.getImageId()),
                contentDescription = null
            )
        }
    }
}

private fun Gender.getImageId(): Int {
    return when (this) {
        Gender.MALE -> R.drawable.male
        Gender.FEMALE -> R.drawable.female
    }
}

@Preview
@Composable
fun GenderCardUiPreview() {
    BMICalculatorTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GenderCardUi(gender = Gender.MALE, isSelected = true)
            GenderCardUi(gender = Gender.FEMALE, isSelected = false)
        }
    }
}