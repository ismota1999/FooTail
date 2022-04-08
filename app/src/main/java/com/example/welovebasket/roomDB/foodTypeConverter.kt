package com.example.welovebasket.roomDB

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class foodTypeConverter {

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