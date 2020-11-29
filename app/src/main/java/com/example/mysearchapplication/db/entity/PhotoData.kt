package com.example.mysearchapplication.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Searches")
data class PhotoData(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("url_m")
    val url: String? = null,
    var query: String = "",
    var pageNumber: Int = 1
): Serializable