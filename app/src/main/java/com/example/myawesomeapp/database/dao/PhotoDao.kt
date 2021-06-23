package com.example.myawesomeapp.database.dao

import androidx.room.*
import com.example.myawesomeapp.models.PhotoDb

private const val TABLE_PHOTO_NAME = "PhotoDb"

@Dao
interface PhotoDao {
    @Query("SELECT * FROM $TABLE_PHOTO_NAME")
    fun getAllPhotos(): List<PhotoDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(items: List<PhotoDb>)
}