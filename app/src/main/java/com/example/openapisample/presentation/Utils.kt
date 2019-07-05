package com.example.openapisample.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any> LifecycleOwner.observe(liveData: LiveData<T>, callback: (T) -> Unit) {
    liveData.observe(this, Observer {
        if (it != null) callback(it)
    })
}