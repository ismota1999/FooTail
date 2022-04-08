package com.example.welovebasket.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.welovebasket.classes.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(foodTypeConverter::class)
abstract class foodDB : RoomDatabase() {
    abstract fun foodDAO():foodDAO

    companion object{
        @Volatile
        var Instance:foodDB? = null

        @Synchronized
        fun getInstance(context: Context):foodDB{
            if(Instance==null){
                Instance = Room.databaseBuilder(
                    context,
                    foodDB::class.java,
                    "mealDB"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return Instance as foodDB
        }
    }
}