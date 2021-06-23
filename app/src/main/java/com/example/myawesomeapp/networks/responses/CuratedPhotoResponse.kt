package com.example.myawesomeapp.networks.responses

import com.example.myawesomeapp.models.PhotoDb
import com.google.gson.annotations.SerializedName

class CuratedPhotoResponse(
    @SerializedName("next_page")
    val nextPage: String?,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("photos")
    val photos: List<PhotoDb>,
    @SerializedName("total_results")
    val totalResults: Int
)