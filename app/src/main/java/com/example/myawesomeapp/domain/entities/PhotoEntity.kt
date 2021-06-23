package com.example.myawesomeapp.domain.entities

data class PhotoEntity(
    val id: Int,
    val photographer: String,
    val photographerId: Int,
    val src: PhotoSourceEntity
)