package com.example.myawesomeapp.data.local

import com.example.myawesomeapp.models.PhotoDb

interface LocalDataSource {
    suspend fun getCuratedPhotos(): List<PhotoDb>?
    suspend fun addPhoto(photos: List<PhotoDb>)
}