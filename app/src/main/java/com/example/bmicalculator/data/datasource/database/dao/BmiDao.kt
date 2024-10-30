package com.example.bmicalculator.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bmicalculator.data.datasource.database.entity.BmiEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BmiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBmiData(record: BmiEntity)

    @Query(SELECT_ALL)
    fun getAllBmiFlow(): Flow<List<BmiEntity>>

    @Query(SELECT_ALL)
    suspend fun getAllBmi(): List<BmiEntity>

    @Query(DELETE_BMI)
    suspend fun deleteBmiCalc(id: Int)

    @Query("SELECT * FROM bmi_data WHERE accountId = :accountId")
    fun getBmiByAccount(accountId: Int): Flow<List<BmiEntity>>

    @Update
    fun updateBmi(bmiEntity: BmiEntity)

    companion object {
        private const val SELECT_ALL = "SELECT * FROM bmi_Data"
        private const val DELETE_BMI = "DELETE FROM bmi_Data WHERE id= :id"
    }
}