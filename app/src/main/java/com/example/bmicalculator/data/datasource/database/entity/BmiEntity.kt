package com.example.bmicalculator.data.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bmicalculator.domain.model.BmiModel
import com.example.bmicalculator.domain.model.BmiModelResult

@Entity("bmi_data")
data class BmiEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val age:Int,
    val height:Int,
    val weight:Int
)


@Entity("bmi_result")
data class BmiCalculationResult(
    val bmi:Float,
    val bmiCategory:String
)

fun BmiEntity.toDomainModel()=BmiModel(
    age=age,
    height=height,
    weight=weight

)

fun BmiCalculationResult.toDomainModel()=BmiModelResult(
    bmi=bmi,
    bmiCategory=bmiCategory
)