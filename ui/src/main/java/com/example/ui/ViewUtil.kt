package com.example.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun View.setShow(show: Boolean?) {
    if (show == null) return

    visibility = if (show) View.VISIBLE else View.GONE
}

fun TextView.setTextFromNumber(number: Number?) {
    if (number == null) return

    text = number.toString()
}

fun ImageView.setRemoteSrc(url: String?, centerCrop: Boolean?) {
    if (url == null || url.isEmpty()) return

    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply {
            if (centerCrop == true)
                centerCrop()
        }
        .into(this)
}