package com.example.myawesomeapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myawesomeapp.database.dao.PhotoDao
import com.example.myawesomeapp.models.PhotoDb
import com.example.myawesomeapp.models.PhotoSource

@Database(entities = [PhotoDb::class, PhotoSource::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao
}