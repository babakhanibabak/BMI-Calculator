package com.example.bmicalculator.data.datasource.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.bmicalculator.data.datasource.database.converter.LocalDateTimeConverter
import com.example.bmicalculator.data.datasource.database.entity.BmiEntity.Companion.BMI_COLUMN
import com.example.bmicalculator.domain.model.BmiModel
import java.time.LocalDateTime

@Entity(
    "BmiData",
    indices = [Index(value = [BMI_COLUMN], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
@TypeConverters(LocalDateTimeConverter::class)
data class BmiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "user_id")
    val userId: Long = 0,
    val age: Int,
    val height: Double,
    val weight: Double,
    @ColumnInfo(BMI_COLUMN)
    val bmi: Double,
    val idealWeight: Double,
    val bodyFat: Double,
    val date: LocalDateTime = LocalDateTime.now(),
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
    date = date,
)

fun BmiModel.toEntity() = BmiEntity(
    userId = userId,
    age = age,
    height = height,
    weight = weight,
    bmi = bmi,
    bodyFat = bodyFat,
    idealWeight = idealWeight,
    date = date,
)


