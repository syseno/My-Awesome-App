package com.example.myawesomeapp.repositories

import com.example.myawesomeapp.data.local.LocalDataSource
import com.example.myawesomeapp.data.remote.RemoteDataSource
import com.example.myawesomeapp.models.PhotoDb

class CuratedRepositoryImpl(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
): CuratedRepository {
    override suspend fun getCuratedPhotos(page: Int, perPage: Int): List<PhotoDb>? {
        val remoteResult = remote.getCuratedPhotos(page, perPage)
        val localResult = local.getCuratedPhotos()
        return if (remoteResult?.isEmpty() == true && localResult?.isEmpty() == true) {
            emptyList()
        } else if (remoteResult?.isNotEmpty() == true) {
            remoteResult
        } else {
            localResult
        }
    }
}