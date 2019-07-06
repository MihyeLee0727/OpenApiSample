package com.example.openapisample.presentation.detail.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.openapisample.BR
import kotlin.properties.Delegates

class DetailViewState : BaseObservable() {
    var imageUrl: String by Delegates.observable(
        initialValue = "",
        onChange = { _, _, newValue ->
            hasImage = newValue.isNotBlank()
            notifyPropertyChanged(BR.imageUrl)
        }
    )
        @Bindable get

    var hasImage: Boolean = false
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.hasImage)
        }

    var contents: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.contents)
        }

    var userProfileImageUrl: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.userProfileImageUrl)
        }

    var userProfileBgImageUrl: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.userProfileBgImageUrl)
        }

    var userName: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.userName)
        }

    var retweetCount: Int = 0
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.retweetCount)
        }

    var favoriteCount: Int = 0
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.favoriteCount)
        }
}