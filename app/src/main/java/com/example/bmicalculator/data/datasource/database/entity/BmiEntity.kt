package com.example.bmicalculator.data.datasource.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.bmicalculator.data.datasource.database.entity.BmiEntity.Companion.BMI_COLUMN
import com.example.bmicalculator.domain.model.BmiModel

@Entity(
    "BmiData",
    indices = [Index(value = [BMI_COLUMN], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class BmiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(index = true)
    val userId: Long = 0,
    val age: Int,
    val height: Double,
    val weight: Double,
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
    userId = userId,
    age = age,
    height = height,
    weight = weight,
    bmi = bmi,
    idealWeight = idealWeight,
    bodyFat = bodyFat,
)

fun BmiModel.toEntity() = BmiEntity(
    userId = userId,
    age = age,
    height = height,
    weight = weight,
    bmi = bmi,
    bodyFat = bodyFat,
    idealWeight = idealWeight,
)


