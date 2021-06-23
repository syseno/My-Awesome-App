package com.example.myawesomeapp.data.remote

import com.example.myawesomeapp.models.PhotoDb
import com.example.myawesomeapp.networks.EndpointApi

class RemoteDataSourceImpl(private val api: EndpointApi): RemoteDataSource {
    override suspend fun getCuratedPhotos(page: Int, perPage: Int): List<PhotoDb>? {
        val call = api.getCuratedPhotos(page, perPage)
        val response = call.execute()
        return response.body()?.photos
    }
}