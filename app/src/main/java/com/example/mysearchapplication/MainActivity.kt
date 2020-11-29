package com.example.mysearchapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysearchapplication.viewmodels.PhotoListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var searchBox: AppCompatEditText
    private lateinit var searchButton: ImageButton
    private lateinit var imagesGrid: RecyclerView
    private lateinit var adapter: ImageAdapter
    private val photosViewModel: PhotoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val utility = NetworkUtility(applicationContext)
        utility.registerNetworkCallback()
        searchBox = image_search_box
        searchButton = search_btn
        imagesGrid = image_grid
        imagesGrid.layoutManager = GridLayoutManager(this,2)
        adapter = ImageAdapter()
        imagesGrid.adapter = adapter
        searchButton.setOnClickListener {
            val query = searchBox.text?.trim() ?: ""
            photosViewModel.setQuery(query.toString())
        }
        photosViewModel.repos.observe(this, Observer {
            adapter.submitList(it)
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menu?.add(0, 2,Menu.NONE," 2 ")
        menu?.add(0, 3,Menu.NONE," 3 ")
        menu?.add(0, 4,Menu.NONE," 4 ")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         super.onOptionsItemSelected(item)
        when(item.itemId) {
            2 -> {
                imagesGrid.layoutManager = GridLayoutManager(this,2)
            }
            3 -> {
                imagesGrid.layoutManager = GridLayoutManager(this,3)
            }
            4 -> {
                imagesGrid.layoutManager = GridLayoutManager(this,4)

            }
            else -> {
                imagesGrid.layoutManager = GridLayoutManager(this,2)

            }
        }
        return false
    }

}