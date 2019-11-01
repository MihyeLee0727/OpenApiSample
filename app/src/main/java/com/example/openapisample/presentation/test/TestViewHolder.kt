package com.example.openapisample.presentation.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.openapisample.R
import com.example.openapisample.databinding.ItemMergedBinding
import com.example.openapisample.databinding.ItemNormalBinding
import com.example.openapisample.presentation.AbsBindingViewHolder
import com.example.openapisample.presentation.main.viewmodel.item.TweetItemViewModel

class NormalViewHolder(
    itemView: View
) : AbsBindingViewHolder<TweetItemViewModel, ItemNormalBinding>(itemView) {

    companion object {
        fun create(
            parent: ViewGroup
        ) = NormalViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_normal,
                parent,
                false
            )
        )
    }

    override fun onBind(binding: ItemNormalBinding, viewModel: TweetItemViewModel, position: Int) {
        binding.viewModel = viewModel
    }

}

class MergedViewHolder(
    itemView: View
) : AbsBindingViewHolder<TweetItemViewModel, ItemMergedBinding>(itemView) {

    companion object {
        fun create(
            parent: ViewGroup
        ) = MergedViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_merged,
                parent,
                false
            )
        )
    }

    override fun onBind(binding: ItemMergedBinding, viewModel: TweetItemViewModel, position: Int) {
        binding.viewModel = viewModel
    }

}