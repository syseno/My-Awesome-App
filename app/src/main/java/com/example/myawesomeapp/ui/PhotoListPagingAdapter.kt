package com.example.myawesomeapp.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myawesomeapp.R
import com.example.myawesomeapp.domain.entities.PhotoEntity
import com.example.myawesomeapp.ext.dpToPix
import com.example.myawesomeapp.ext.inflate
import com.example.myawesomeapp.paging.PagingItem
import com.example.myawesomeapp.paging.PagingUpdater
import com.example.myawesomeapp.ui.viewholder.PhotoGridDataViewHolder
import com.example.myawesomeapp.ui.viewholder.PhotoListDataViewHolder
import com.example.myawesomeapp.ui.viewholder.PhotoListFooterViewHolder
import com.example.myawesomeapp.ui.viewholder.PhotoListHeaderViewHolder

class PhotoListPagingAdapter(
    pagingUpdater: PagingUpdater<PhotoEntity>,
    initialLoad: Boolean = false,
    private val photoCardCornerRadius: Int,
    private val onPhotoClickListener: ((photoModel: PhotoEntity) -> Unit)? = null
) : PagingAdapter<RecyclerView.ViewHolder, PhotoEntity>(
    pagingUpdater,
    DIFF_CALLBACK,
    initialLoad
) {
    var VIEW_TYPE = 0

    fun setViewType(viewType: Int) {
        VIEW_TYPE = viewType
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var holder: RecyclerView.ViewHolder
        when (viewType) {
            PagingItem.ItemType.LIST.itemCode -> {
                val view = parent.inflate(R.layout.item_photo_in_list, parent, false)
                holder = PhotoListDataViewHolder(
                    view,
                    view.context.dpToPix(photoCardCornerRadius)
                )
            }
            PagingItem.ItemType.GRID.itemCode -> {
                val view = parent.inflate(R.layout.item_photo_in_grid, parent, false)
                holder = PhotoGridDataViewHolder(
                    view,
                    view.context.dpToPix(photoCardCornerRadius)
                )
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            PagingItem.ItemType.LIST.itemCode -> {
                items[position].data?.let { photo ->
                    (holder as PhotoListDataViewHolder).apply {
                        bind(photo)
                        setOnClickListener { onPhotoClickListener?.invoke(photo) }
                    }
                }
            }
            PagingItem.ItemType.GRID.itemCode -> {
                items[position].data?.let { photo ->
                    (holder as PhotoGridDataViewHolder).apply {
                        bind(photo)
                        setOnClickListener { onPhotoClickListener?.invoke(photo) }
                    }
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PhotoEntity>() {
            override fun areItemsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PhotoEntity, newItem: PhotoEntity): Boolean {
                return oldItem.src.medium == newItem.src.medium
            }
        }
    }
}