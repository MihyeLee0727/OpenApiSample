package com.example.openapisample.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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

@BindingAdapter("remoteSrc")
fun ImageView.setRemoteSrc(url: String?) {
    if (url == null || url.isEmpty()) return

    Glide.with(context).load(url).centerCrop().into(this)
}