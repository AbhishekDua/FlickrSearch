package com.example.mysearchapplication.network

import com.example.mysearchapplication.db.PhotosObj
import com.example.mysearchapplication.network.GetPhotosService
import com.example.mysearchapplication.network.PhotosRepository
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Implementation of [RemoteRepository] using retrofit library
 */
class FlickrPhotosApi : PhotosRepository {

    val flickrPhotoService: GetPhotosService by lazy { retrofit.create(GetPhotosService::class.java) }

    override suspend fun getPhotos(keyWord: String,pageNumber:Int, perPage: Int) : PhotosObj {
        return  flickrPhotoService.getPhotos(keyWord, pageNumber,perPage)
    }


    companion object {
        private const val BASE_URL = "https://www.flickr.com/services/"

        private val gson = GsonBuilder().create()

        private val retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL).build()

    }

}