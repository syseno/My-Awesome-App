package com.example.myawesomeapp.ui.viewholder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myawesomeapp.paging.PagingItem

class PhotoListFooterViewHolder(
    view: View,
    val height: Int
) : RecyclerView.ViewHolder(view) {

    fun bind(data: PagingItem.ItemData) {
        itemView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
    }
}
