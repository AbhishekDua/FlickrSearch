package com.example.mysearchapplication.db


import androidx.paging.LivePagedListBuilder
import com.example.mysearchapplication.BoundaryCallbackImpl
import com.example.mysearchapplication.network.PhotosRepository
import kotlinx.coroutines.CoroutineScope

class PhotosRepositoryImpl (
    val remoteRepository: PhotosRepository,
    val localDb: LocalDb,
    val coroutineScope: CoroutineScope
) {

    fun search(query: String, perPage:Int = 36): PhotosRepoResult {
        val dataSourceFactory = localDb.photosForQuery(query)
        val boundaryCallback = BoundaryCallbackImpl(localDb,remoteRepository,query,perPage,coroutineScope)
        val data = LivePagedListBuilder(dataSourceFactory,36).setBoundaryCallback(boundaryCallback).build()
        return PhotosRepoResult(data)
    }
}