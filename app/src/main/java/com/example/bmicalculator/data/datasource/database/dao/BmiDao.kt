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
    suspend fun insertBmis(list: List<BmiEntity>)

    @Query(SELECT_ALL)
    fun getAllBmisFlow(): Flow<BmiEntity>

    @Query(SELECT_ALL)
    fun getAllBmi(): List<BmiEntity>

    @Query(DELETE_Bmi)
    suspend fun deleteBmi(id: Int)


    @Update
    fun updateBmi(location: BmiEntity)

    companion object {
        private const val SELECT_ALL = "SELECT * FROM bmi"
        private const val DELETE_Bmi = "DELETE FROM bmi WHERE id= :id"
    }
}