package com.example.bmicalculator.data.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bmicalculator.domain.model.BmiModel
import com.example.bmicalculator.domain.model.BmiModelResult

@Entity("bmi_data")
data class BmiEntity(
    @PrimaryKey(autoGenerate = true) val id:Int=0,
    val age:Float,
    val height:Float,
    val weight:Float,
    val gender:String,
    val bmi:Float,
    val idealWeight:Float,
    val bodyFat:Float
)

