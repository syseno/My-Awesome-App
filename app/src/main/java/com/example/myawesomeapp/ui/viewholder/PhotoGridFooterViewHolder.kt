package com.example.myawesomeapp.ui.viewholder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myawesomeapp.paging.PagingItem

class PhotoGridFooterViewHolder(
    view: View,
    val width: Int,
    val height: Int
) : RecyclerView.ViewHolder(view) {

    fun bind(data: PagingItem.ItemData, useHalfOfHeight: Boolean = false) {
        itemView.layoutParams = ViewGroup.LayoutParams(
            width,
            if (useHalfOfHeight) height / 2 else height
        )
    }
}