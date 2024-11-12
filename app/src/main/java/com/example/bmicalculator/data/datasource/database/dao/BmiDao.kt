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
    suspend fun deleteBmiCalc(id: Long)

    @Query("SELECT * FROM bmidata WHERE user_id = :userId")
    fun getBmiForUserFlow(userId: Long): Flow<List<BmiEntity>>

    @Update
    fun updateBmi(bmiEntity: BmiEntity)

    companion object {
        private const val SELECT_ALL = "SELECT * FROM BmiData"
        private const val DELETE_BMI = "DELETE FROM BmiData WHERE id= :id"
    }
}