package com.example.bmicalculator.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun UserInfoContainer(
    fullName: String,
    gender: Gender,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(gender.getImageId()),
                contentDescription = null
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = fullName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(text = gender.name)
            }
        }
    }
}

@Preview
@Composable
fun UserInfoContainerPreview() {
    BMICalculatorTheme {
        UserInfoContainer(
            fullName = "John Doe",
            gender = Gender.MALE,
        )
    }
}