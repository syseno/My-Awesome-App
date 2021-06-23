package com.example.myawesomeapp.repositories

import com.example.myawesomeapp.models.PhotoDb

interface CuratedRepository {
    suspend fun getCuratedPhotos(page: Int, perPage: Int): List<PhotoDb>?
}