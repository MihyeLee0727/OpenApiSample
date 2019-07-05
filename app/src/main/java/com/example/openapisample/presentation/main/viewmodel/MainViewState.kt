package com.example.openapisample.presentation.main.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.openapisample.BR

class MainViewState : BaseObservable() {
    var searchKeyword: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.searchKeyword)
        }
}