package com.example.bmicalculator.data.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.bmicalculator.data.datasource.database.converter.GenderEnumConverter
import com.example.bmicalculator.domain.model.BmiModel
import com.example.bmicalculator.domain.model.Gender

@Entity("bmi_data")
@TypeConverters(GenderEnumConverter::class)
data class BmiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val age: Int,
    val height: Double,
    val weight: Double,
    val gender: Gender,
    val bmi: Double,
    val idealWeight: Double,
    val bodyFat: Double,
)

fun BmiEntity.toDomain() = BmiModel(
    age = age,
    height = height,
    weight = weight,
    gender = gender,
    bmi = bmi,
    idealWeight = idealWeight,
    bodyFat = bodyFat,
)

fun BmiModel.toEntity() = BmiEntity(
    age = age,
    height = height,
    weight = weight,
    gender = gender,
    bmi = bmi,
    bodyFat = bodyFat,
    idealWeight = idealWeight,
)

