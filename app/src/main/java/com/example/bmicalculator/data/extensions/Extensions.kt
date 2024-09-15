package com.example.bmicalculator.data.extensions

import java.util.Locale

fun Double.formatBmiValue(): String {
    return String.format(Locale.US, "%.2f", this)
}