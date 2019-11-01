package com.example.openapisample.presentation.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.openapisample.R
import com.example.openapisample.databinding.ItemTweetBinding
import com.example.openapisample.presentation.AbsBindingViewHolder
import com.example.openapisample.presentation.main.viewmodel.item.TweetItemViewModel

class MainViewHolder(
    itemView: View
) : AbsBindingViewHolder<TweetItemViewModel, ItemTweetBinding>(itemView) {

    companion object {
        fun create(
            parent: ViewGroup
        ) = MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_tweet,
                parent,
                false
            )
        )
    }

    override fun onBind(binding: ItemTweetBinding, viewModel: TweetItemViewModel, position: Int) {
        binding.viewModel = viewModel
    }

}

