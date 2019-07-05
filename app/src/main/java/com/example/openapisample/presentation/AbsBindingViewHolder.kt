package com.example.openapisample.presentation

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class AbsBindingViewHolder<VM : Any, B : ViewDataBinding>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    val binding: B? = DataBindingUtil.bind(itemView)

    abstract fun onBind(binding: B, viewModel: VM, position: Int)
}