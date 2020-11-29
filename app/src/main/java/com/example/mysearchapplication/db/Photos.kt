package com.example.mysearchapplication.db

import com.example.mysearchapplication.db.entity.PhotoData
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Photos(
    @SerializedName("page")
    val pageNumber: Int,
    @SerializedName("perpage")
    val perpage: Int,
    @SerializedName("photo")
    val photo: List<PhotoData>,
    var queriedText: String = ""
): Serializable