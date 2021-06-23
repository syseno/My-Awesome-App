package com.example.myawesomeapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myawesomeapp.R
import com.example.myawesomeapp.ext.dpToPix
import com.example.myawesomeapp.paging.PagingItem
import com.example.myawesomeapp.tools.ProgressState
import com.example.myawesomeapp.ui.decorations.PhotoListItemDecoration
import com.example.myawesomeapp.ui.viewholder.PlaceholderView
import com.example.myawesomeapp.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val homeViewModel by viewModel<MainViewModel>()
    private val photoCardMargin by lazy { this.dpToPix(16).toInt() }
    private lateinit var photoListPagingAdapter: PhotoListPagingAdapter
    private var recyclerState: Parcelable? = null
    private val contentView by lazy { homeRecyclerView }
    private val placeholderView by lazy { placeholder_view }
    private var isList: Boolean? = true

    private var linearLayoutManager: LinearLayoutManager? = null
    private var gridLayoutManager: GridLayoutManager? = null

    companion object {
        const val KEY_EXTRA_DESCRIPTION = "key_description"
        const val KEY_EXTRA_IMG_URL = "key_img_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initPhotoListPagingAdapter()
        initRvList()
        initObserver()
        initModelObserving()
        linearLayoutManager = LinearLayoutManager(this)
        gridLayoutManager = GridLayoutManager(this, 2)
    }

    private fun initRvList() {
        homeRecyclerView.adapter = photoListPagingAdapter
        homeRecyclerView.addItemDecoration(
            PhotoListItemDecoration(photoCardMargin)
        )
    }

    private fun initPhotoListPagingAdapter() {
        photoListPagingAdapter = PhotoListPagingAdapter(
            homeViewModel.photoPagingUpdater,
            true,
            16
        ) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(KEY_EXTRA_DESCRIPTION, it.photographer)
            intent.putExtra(KEY_EXTRA_IMG_URL, it.src.small)
            startActivity(intent)
        }
    }

    private fun initModelObserving() {
        homeViewModel.progressState.observe(this, Observer { progressState ->
            when (progressState) {
                is ProgressState.DONE -> {
                    placeholderView?.setState(PlaceholderView.PlaceholderState.GONE)
                    contentView?.isVisible = true
                }
                is ProgressState.LOADING -> {
                    placeholderView?.setState(PlaceholderView.PlaceholderState.LOADING)
                    contentView?.isVisible = false
                }
                is ProgressState.ERROR -> {
                    placeholderView?.setState(PlaceholderView.PlaceholderState.ERROR)
                    contentView?.isVisible = false
                }
                is ProgressState.INITIAL -> {
                    placeholderView?.setState(PlaceholderView.PlaceholderState.INITIAL)
                    contentView?.isVisible = false
                }
                is ProgressState.EMPTY -> {
                    placeholderView?.setState(PlaceholderView.PlaceholderState.EMPTY)
                    contentView?.isVisible = false
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onPause() {
        super.onPause()
        homeRecyclerView.layoutManager?.onSaveInstanceState()?.let { state ->
            recyclerState = state
        }
    }

    private fun initObserver() {
        homeViewModel.photos.observe(this, Observer {
            photoListPagingAdapter.items = it
        })
    }

    override fun onResume() {
        super.onResume()
        recyclerState?.let { state ->
            homeRecyclerView.layoutManager?.onRestoreInstanceState(state)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuSwitchLayout) {
            switchIcon(item)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun switchIcon(item: MenuItem) {
        if (isList == true) {
            item.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid)
            isList = false
            photoListPagingAdapter.setViewType(PagingItem.ItemType.GRID.itemCode)
            homeRecyclerView.layoutManager = gridLayoutManager
            homeRecyclerView.adapter = photoListPagingAdapter
        } else {
            item.icon = ContextCompat.getDrawable(this, R.drawable.ic_list)
            isList = true
            photoListPagingAdapter.setViewType(PagingItem.ItemType.LIST.itemCode)
            homeRecyclerView.layoutManager = linearLayoutManager
            homeRecyclerView.adapter = photoListPagingAdapter
        }
    }
}