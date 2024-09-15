package com.example.bmicalculator.data.datasource.database.converter

import androidx.room.TypeConverter
import com.example.bmicalculator.domain.model.Gender

class GenderEnumConverter {
    @TypeConverter
    fun fromGender(gender: Gender): String {
        return gender.name
    }

    @TypeConverter
    fun toGender(gender: String): Gender {
        return Gender.valueOf(gender)
    }
}