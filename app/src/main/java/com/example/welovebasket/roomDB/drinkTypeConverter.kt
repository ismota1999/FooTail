package com.example.welovebasket.roomDB

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.jar.Attributes

@TypeConverters
class drinkTypeConverter {

    @TypeConverter
    fun fromAnytoString(attribute:Any?) : String{
        if (attribute == null)
            return ""
        return attribute.toString()
    }

    @TypeConverter
    fun fromStringToAny(attribute: String?) : Any{
        if (attribute == null)
            return ""
        return attribute
    }
}