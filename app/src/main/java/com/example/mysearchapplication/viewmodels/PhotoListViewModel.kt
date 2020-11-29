package com.example.mysearchapplication.viewmodels

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.mysearchapplication.db.PhotosRepoResult
import com.example.mysearchapplication.db.LocalDb
import com.example.mysearchapplication.db.entity.PhotoData
import com.example.mysearchapplication.db.PhotosRepositoryImpl
import com.example.mysearchapplication.network.FlickrPhotosApi

class PhotoListViewModel(application: Application) : AndroidViewModel(application) {
    private val photoQuery = MutableLiveData<String>()
    private val repository =
        PhotosRepositoryImpl(FlickrPhotosApi(), LocalDb(context = application), viewModelScope)
    private val repoResult: LiveData<PhotosRepoResult> = Transformations.map(photoQuery) {
        repository.search(it)
    }

    val repos: LiveData<PagedList<PhotoData>> = Transformations.switchMap(repoResult) { it.data }

    fun setQuery(query: String) {
        if (query.isNotEmpty()) {
            photoQuery.postValue(query)
        }
    }
}