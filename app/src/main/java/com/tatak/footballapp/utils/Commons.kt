package com.tatak.footballapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tatak.footballapp.R

internal fun loadImage(context: Context,
                       url: String,
                       imageView: ImageView
) {

    fun setMemoryCategory(context: Context) {
        Glide.get(context).setMemoryCategory(MemoryCategory.NORMAL)
    }

    setMemoryCategory(context)

    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_soccer)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(imageView)
}