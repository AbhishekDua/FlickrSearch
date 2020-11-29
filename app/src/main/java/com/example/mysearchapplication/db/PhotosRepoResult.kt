package com.example.mysearchapplication.db

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.mysearchapplication.db.entity.PhotoData

data class PhotosRepoResult(val data: LiveData<PagedList<PhotoData>>)