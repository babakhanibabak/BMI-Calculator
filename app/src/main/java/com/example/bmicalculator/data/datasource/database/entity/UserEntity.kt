package com.example.bmicalculator.data.datasource.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.bmicalculator.data.datasource.database.converter.GenderEnumConverter
import com.example.bmicalculator.domain.model.Gender
import com.example.bmicalculator.domain.model.UserModel

@Entity(tableName = "Users")
@TypeConverters(GenderEnumConverter::class)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Long = 0,
    val fullName: String,
    val gender: Gender,
)

fun UserEntity.toDomain() = UserModel(
    id = id,
    fullName = fullName,
    gender = gender,
)

fun UserModel.toEntity() = UserEntity(
    id = id,
    fullName = fullName,
    gender = gender,
)