package com.example.welovebasket.roomDB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.welovebasket.classes.Meal


@Dao
interface foodDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Query("SELECT * FROM mealInfo")
    fun getAllMeals():LiveData<List<Meal>>
}