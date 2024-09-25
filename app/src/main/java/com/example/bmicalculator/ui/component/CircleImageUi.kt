package com.example.bmicalculator.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

@Composable
fun CircleImageUi(
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(20.dp, 20.dp),
    @DrawableRes imageId: Int,
) {
    Image(
        modifier = modifier
            .clip(CircleShape)
            .border(BorderStroke(1.dp, Color.Black), CircleShape)
            .size(size),
        painter = painterResource(id = imageId), contentDescription = ""
    )

}

@Preview
@Composable
private fun CircleImageUiPreview() {
    BMICalculatorTheme {
        CircleImageUi(imageId = R.drawable.ic_launcher_foreground)
    }
}