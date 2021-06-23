package com.example.myawesomeapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PhotoDb(
    @ColumnInfo
    @SerializedName("height")
    val height: Int,
    @PrimaryKey
    @ColumnInfo
    @SerializedName("id")
    val id: Int,
    @ColumnInfo
    @SerializedName("photographer")
    val photographer: String,
    @ColumnInfo
    @SerializedName("photographer_id")
    val photographerId: Int,
    @ColumnInfo
    @SerializedName("photographer_url")
    val photographerUrl: String,
    @ColumnInfo
    @SerializedName("src")
    val src: PhotoSource,
    @ColumnInfo
    @SerializedName("url")
    val url: String,
    @ColumnInfo
    @SerializedName("width")
    val width: Int
)