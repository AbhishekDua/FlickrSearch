package com.example.mysearchapplication.db

import android.content.Context
import androidx.paging.DataSource
import com.example.mysearchapplication.db.database.AppDatabase
import com.example.mysearchapplication.db.entity.PhotoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDb (context: Context) {
    val appDataBase = AppDatabase.getInstance(context)

    fun insertData(query: String, photosList: List<PhotoData>, pageNumber: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            photosList.forEach {
                it.query = query
                it.pageNumber = pageNumber
            }
            appDataBase.userSearchDao().addPhotosForQuery(photosList)
        }
    }

    fun photosForQuery(query: String): DataSource.Factory<Int, PhotoData> {
        return appDataBase.userSearchDao().getPhotos(query)
    }
}