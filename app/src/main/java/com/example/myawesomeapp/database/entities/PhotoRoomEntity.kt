package com.example.myawesomeapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoRoomEntity(
    @PrimaryKey
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    val normalPhotoUrl: String,
    @ColumnInfo
    val bigPhotoUrl: String,
    @ColumnInfo
    val photographer: String,
    @ColumnInfo
    val photographerUrl: String,
)