package com.example.mysearchapplication

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.paging.PagedList
import com.example.mysearchapplication.db.LocalDb
import com.example.mysearchapplication.db.entity.PhotoData
import com.example.mysearchapplication.network.PhotosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BoundaryCallbackImpl(
    val localDb: LocalDb,
    val remoteRepository: PhotosRepository,
    val keyword: String,
    val perPage: Int,
    val scope: CoroutineScope
) : PagedList.BoundaryCallback<PhotoData>() {

    private var  lastRequestedPage = 1

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: PhotoData) {
        if(itemAtEnd.pageNumber > lastRequestedPage) {
            lastRequestedPage = itemAtEnd.pageNumber + 1
        }

        requestAndSaveData()
    }

    fun requestAndSaveData() {
        scope.launch(Dispatchers.IO) {
            if(NetworkUtility.isNetworkConnected) {
                val obj = remoteRepository.getPhotos(keyword, lastRequestedPage, perPage)
                if (obj.status.equals("ok", true)) {
                    localDb.insertData(keyword, obj.photos.photo, obj.photos.pageNumber)
                    lastRequestedPage++
                }
            }
        }
    }
}