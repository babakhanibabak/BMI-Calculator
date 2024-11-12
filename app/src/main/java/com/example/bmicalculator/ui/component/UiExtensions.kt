package com.example.bmicalculator.ui.component

import com.example.bmicalculator.R
import com.example.bmicalculator.domain.model.Gender

fun Gender.getImageId(): Int {
    return when (this) {
        Gender.MALE -> R.drawable.male
        Gender.FEMALE -> R.drawable.female
    }
}