package com.example.mysearchapplication.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mysearchapplication.db.entity.PhotoData

@Dao
interface UserSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPhotosForQuery(photosList: List<PhotoData>)
    @Query("select * from Searches where `query` = :queryStr")
    fun getPhotos(queryStr: String): DataSource.Factory<Int, PhotoData>
}