package com.example.bmicalculator.ui.bmi

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.example.bmicalculator.R
import com.example.bmicalculator.ui.theme.normalColor
import com.example.bmicalculator.ui.theme.obesityIColor
import com.example.bmicalculator.ui.theme.obesityIIColor
import com.example.bmicalculator.ui.theme.obesityIIIColor
import com.example.bmicalculator.ui.theme.overweightColor
import com.example.bmicalculator.ui.theme.underweightColor

object BmiClassificationMapper {

    fun mapClassifications(bmi: String?, context: Context): List<BmiBmiClassificationItem> {
        val bmiNumber = bmi?.toDoubleOrNull()
        val classification = getBmiClassification(bmiNumber)
        val items = mutableListOf<BmiBmiClassificationItem>()
        BmiClassification.entries.forEach {
            if (it != BmiClassification.UNKNOWN) {
                val color = if (classification == it) getClassificationColor(it) else Color.Black
                items.add(
                    BmiBmiClassificationItem(
                        title = getClassificationTitle(it, context),
                        color = color,
                        backgroundColor = if (color == Color.Black) Color.Transparent else color.copy(
                            alpha = 0.2f
                        ),
                    )
                )
            }
        }
        return items
    }

    fun getClassificationColor(bmi: Double): Color {
        val classification = getBmiClassification(bmi)
        return getClassificationColor(classification)
    }

    private fun getClassificationTitle(
        classification: BmiClassification,
        context: Context,
    ): String {
        return when (classification) {
            BmiClassification.UNKNOWN -> ""
            BmiClassification.UNDER_WEIGHT -> context.getString(R.string.underweight)
            BmiClassification.NORMAL -> context.getString(R.string.normal_weight)
            BmiClassification.OVER_WEIGHT -> context.getString(R.string.overweight)
            BmiClassification.OBESE_CLASS_I -> context.getString(R.string.obese_class_i)
            BmiClassification.OBESE_CLASS_II -> context.getString(R.string.obese_class_ii)
            BmiClassification.OBESE_CLASS_III -> context.getString(R.string.obese_class_iii)
        }
    }

    private fun getClassificationColor(classification: BmiClassification): Color {
        return when (classification) {
            BmiClassification.UNKNOWN -> Color.Black
            BmiClassification.UNDER_WEIGHT -> underweightColor
            BmiClassification.NORMAL -> normalColor
            BmiClassification.OVER_WEIGHT -> overweightColor
            BmiClassification.OBESE_CLASS_I -> obesityIColor
            BmiClassification.OBESE_CLASS_II -> obesityIIColor
            BmiClassification.OBESE_CLASS_III -> obesityIIIColor
        }
    }

    private fun getBmiClassification(bmiNumber: Double?): BmiClassification {
        return when {
            bmiNumber == null -> BmiClassification.UNKNOWN
            bmiNumber < 18.5 -> BmiClassification.UNDER_WEIGHT
            bmiNumber in (18.5..24.9) -> BmiClassification.NORMAL
            bmiNumber in (25.0..29.9) -> BmiClassification.OVER_WEIGHT
            bmiNumber in (30.0..34.9) -> BmiClassification.OBESE_CLASS_I
            bmiNumber in (35.0..39.9) -> BmiClassification.OBESE_CLASS_II
            bmiNumber >= 40.0 -> BmiClassification.OBESE_CLASS_III
            else -> BmiClassification.UNKNOWN
        }
    }
}