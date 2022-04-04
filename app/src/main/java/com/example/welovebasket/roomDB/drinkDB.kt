package com.example.welovebasket.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.welovebasket.classes.Drink
import java.security.AccessControlContext

@Database(entities = [Drink::class], version = 2)
@TypeConverters(drinkTypeConverter::class)
abstract class drinkDB : RoomDatabase() {
    abstract fun drinkDAO():drinkDAO

    companion object{
        @Volatile
        var Instance:drinkDB? = null

        @Synchronized
        fun getInstance(context: Context):drinkDB{
            if(Instance==null){
                Instance = Room.databaseBuilder(
                    context,
                    drinkDB::class.java,
                    "cocktailDB"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return Instance as drinkDB
        }
    }
}