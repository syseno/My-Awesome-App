package com.example.myawesomeapp.domain.usecases

import com.example.myawesomeapp.domain.entities.PhotoEntity
import com.example.myawesomeapp.domain.mapper.EntityMapper
import com.example.myawesomeapp.repositories.CuratedRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class DisplayingPhotoUseCase(private val repository: CuratedRepository) {
    suspend fun getCuratedPhotos(page: Int, perPage: Int): List<PhotoEntity>? {
        return withContext(IO) {
            repository.getCuratedPhotos(page, perPage)?.let {
                it.map { photo ->
                    EntityMapper.mapToPhotoEntity(photo)
                }
            }
        }
    }
}