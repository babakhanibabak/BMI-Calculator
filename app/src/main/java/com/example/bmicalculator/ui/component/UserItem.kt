package com.example.bmicalculator.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.ui.common.model.UserUiModel
import com.example.bmicalculator.ui.theme.BMICalculatorTheme
import com.example.bmicalculator.ui.theme.DarkBlue
import com.example.bmicalculator.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserItem(
    model: UserUiModel,
    modifier: Modifier = Modifier,
    onClick: (UserUiModel) -> Unit = {},
    onDelete: (UserUiModel) -> Unit = {},
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    val shape = RoundedCornerShape(12.dp)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = LightBlue.copy(alpha = 0.4f), shape = shape)
            .clip(shape = shape)
            .border(width = 1.dp, color = DarkBlue, shape = shape)
            .clickable { onClick(model) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            text = model.fullName,
            fontWeight = FontWeight.SemiBold,
        )
        IconButton(
            modifier = Modifier.padding(end = 8.dp),
            onClick = { showDeleteDialog = true }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = DarkBlue,
                contentDescription = null,
            )
        }
    }

    if (showDeleteDialog) {
        BasicAlertDialog(onDismissRequest = { showDeleteDialog = false }) {
            Column(
                modifier = Modifier
                    .background(color = Color.White, shape = shape)
                    .border(width = 1.dp, color = DarkBlue, shape = shape)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                Text(text = stringResource(R.string.delete_user_alert_message))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { showDeleteDialog = false }) {
                        Text(text = stringResource(R.string.cancel), color = Color.Red)
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    TextButton(onClick = {
                        onDelete(model)
                        showDeleteDialog = false
                    }) {
                        Text(text = stringResource(R.string.yes))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
    BMICalculatorTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            UserItem(
                model = UserUiModel(
                    id = 1,
                    fullName = "John",
                    gender = Gender.MALE,
                )
            )
        }
    }
}