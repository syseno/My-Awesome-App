package com.example.myawesomeapp.networks

import com.example.myawesomeapp.networks.responses.CuratedPhotoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndpointApi {
    @GET("curated")
    fun getCuratedPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<CuratedPhotoResponse>
}