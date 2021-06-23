package com.example.myawesomeapp.ui.viewholder

import android.graphics.Outline
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.myawesomeapp.domain.entities.PhotoEntity
import kotlinx.android.synthetic.main.item_photo_in_list.view.*

class PhotoListDataViewHolder(
    view: View,
    private val photoCardCornerRadius: Float

) : RecyclerView.ViewHolder(view) {
    private val outlineProvider by lazy {
        object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, photoCardCornerRadius)
            }
        }
    }

    fun bind(item: PhotoEntity) {
        Glide.with(itemView.context)
            .load(item.src.medium)
            .transform(CenterCrop())
            .into(itemView.photo_iv)
        itemView.author_tv.text = item.photographer
        itemView.photo_iv.outlineProvider = outlineProvider
        itemView.photo_iv.clipToOutline = true
    }

    fun setOnClickListener(onClickListener: () -> Unit) {
        itemView.photo_iv.setOnClickListener { onClickListener.invoke() }
    }
}