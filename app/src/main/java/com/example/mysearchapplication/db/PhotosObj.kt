package com.example.mysearchapplication.db

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PhotosObj(
    @SerializedName("photos")
    val photos: Photos,
    @SerializedName("stat")
    val status: String
): Serializable