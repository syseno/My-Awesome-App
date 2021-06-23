package com.example.myawesomeapp.data.remote

import com.example.myawesomeapp.models.PhotoDb

interface RemoteDataSource {
    suspend fun getCuratedPhotos(page: Int, perPage: Int): List<PhotoDb>?
}