package com.example.bmicalculator.data.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("bmi_data")
data class BmiEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val age:Int,
    val height:Int,
    val weight:Int
)

data class BmiCalculationResult(
    val bmi:Float,
    val bmiCategory:String
)
