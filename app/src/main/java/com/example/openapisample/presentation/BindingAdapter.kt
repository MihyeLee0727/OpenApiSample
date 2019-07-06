package com.example.openapisample.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("show")
fun View.setShow(show: Boolean?) {
    if (show == null) return

    visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("textFromNumber")
fun TextView.setTextFromNumber(number: Number?) {
    if (number == null) return

    text = number.toString()
}

@BindingAdapter("remoteSrc", "imgCenterCrop", requireAll = false)
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