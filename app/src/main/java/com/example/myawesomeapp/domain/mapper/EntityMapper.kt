package com.example.myawesomeapp.domain.mapper

import com.example.myawesomeapp.domain.entities.PhotoEntity
import com.example.myawesomeapp.domain.entities.PhotoSourceEntity
import com.example.myawesomeapp.models.PhotoDb

object EntityMapper {
    fun mapToPhotoEntity(src: PhotoDb): PhotoEntity {
        return PhotoEntity(
            src.id,
            src.photographer,
            src.photographerId,
            PhotoSourceEntity(
                src.src.landscape,
                src.src.large,
                src.src.large2x,
                src.src.medium,
                src.src.original,
                src.src.portrait,
                src.src.small,
                src.src.tiny,
            )
        )
    }
}