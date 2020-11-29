package com.example.mysearchapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mysearchapplication.db.entity.PhotoData
import com.squareup.picasso.Picasso

class ImageAdapter : PagedListAdapter<PhotoData, ImageAdapter.ImageViewHolder>(diffCallback) {

    inner class ImageViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.image)
        var photoDataObj: PhotoData? = null
        fun bind(photoData: PhotoData?) {
            photoDataObj = photoData
            if(!photoData?.url.isNullOrEmpty()) {
                Picasso.get().load(photoData?.url).fit().placeholder(R.drawable.gray_background).into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_photo_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<PhotoData>() {
            override fun areContentsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
                return  oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
                return oldItem == newItem
            }
        }
    }

}