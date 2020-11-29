package com.example.mysearchapplication.network

import com.example.mysearchapplication.db.PhotosObj
import retrofit2.http.GET
import retrofit2.http.Query

//keeps changing
const val API_KEY = "d7f66852aed44008ce15c75baaed617a"

interface GetPhotosService {
    @GET("rest/?method=flickr.photos.search&api_key=${API_KEY}&extras=url_m&format=json&nojsoncallback=1")
    suspend fun getPhotos(
        @Query("text") text: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PhotosObj
}