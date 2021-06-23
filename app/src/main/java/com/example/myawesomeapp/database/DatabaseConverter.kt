package com.example.myawesomeapp.database

import androidx.room.TypeConverter
import com.example.myawesomeapp.models.PhotoDb
import com.example.myawesomeapp.models.PhotoSource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStringPhoto(source: String): PhotoDb {
        val type = object : TypeToken<PhotoDb>() {}.type
        return gson.fromJson(source, type)
    }

    @TypeConverter
    fun fromStringPhotoSource(source: String): PhotoSource {
        val type = object : TypeToken<PhotoSource>() {}.type
        return gson.fromJson(source, type)
    }

    @TypeConverter
    fun toStringSource(source: PhotoSource): String {
        val type = object : TypeToken<PhotoSource>() {}.type
        return gson.toJson(source, type)
    }
}