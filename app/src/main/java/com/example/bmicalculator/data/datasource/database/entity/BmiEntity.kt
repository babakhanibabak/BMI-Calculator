package com.example.bmicalculator.data.datasource.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.bmicalculator.data.datasource.database.converter.GenderEnumConverter
import com.example.bmicalculator.data.datasource.database.entity.BmiEntity.Companion.BMI_COLUMN
import com.example.bmicalculator.domain.model.BmiModel
import com.example.bmicalculator.domain.model.Gender

@Entity("bmi_data", indices = [Index(value = [BMI_COLUMN], unique = true)])
@TypeConverters(GenderEnumConverter::class)
data class BmiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val age: Int,
    val height: Double,
    val weight: Double,
    val gender: Gender,
    @ColumnInfo(BMI_COLUMN)
    val bmi: Double,
    val idealWeight: Double,
    val bodyFat: Double,
) {
    companion object {
        const val BMI_COLUMN = "bmi_value"
    }
}

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

