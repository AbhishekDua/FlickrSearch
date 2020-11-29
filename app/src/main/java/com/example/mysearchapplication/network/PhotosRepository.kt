package com.example.mysearchapplication.network

import com.example.mysearchapplication.db.PhotosObj

interface PhotosRepository {
    suspend fun getPhotos(keyWord: String, pageNumber: Int = 1, perPage: Int = 24):PhotosObj
}