package com.example.welovebasket.roomDB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.welovebasket.classes.Drink

@Dao
interface drinkDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAndUpdate(drink:Drink)

    @Delete
    suspend fun deleteDrink(drink: Drink)

    @Query("SELECT * FROM drinkInfo")
    fun getAllDrinks():LiveData<List<Drink>>


}