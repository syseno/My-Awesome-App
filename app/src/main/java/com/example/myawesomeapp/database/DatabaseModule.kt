package com.example.myawesomeapp.database

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module

private const val DB_NAME = "my_awesome_db"

val databaseModule = module {
    single { dbInstance(get()) }
}

fun dbInstance(context: Context): RoomDatabase {
    return Room.databaseBuilder(context, RoomDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()
}