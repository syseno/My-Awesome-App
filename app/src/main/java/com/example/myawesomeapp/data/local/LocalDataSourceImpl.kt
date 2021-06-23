package com.example.myawesomeapp.data.local

import com.example.myawesomeapp.database.RoomDatabase
import com.example.myawesomeapp.models.PhotoDb

class LocalDataSourceImpl(private val database: RoomDatabase): LocalDataSource {
    override suspend fun getCuratedPhotos(): List<PhotoDb>? {
        return database.photoDao().getAllPhotos()
    }

    override suspend fun addPhoto(photos: List<PhotoDb>) {
        database.photoDao().insertPhotos(photos)
    }
}