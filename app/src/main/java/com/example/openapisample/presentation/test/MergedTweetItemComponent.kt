package com.example.openapisample.presentation.test

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.openapisample.R
import com.example.openapisample.databinding.ComponentTweetMergeBinding
import com.example.openapisample.presentation.main.viewmodel.item.TweetItemViewModel

class MergedTweetItemComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ComponentTweetMergeBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.component_tweet_merge,
        this,
        true
    )

    fun setViewModel(viewModel: TweetItemViewModel) {
        binding.viewModel = viewModel
        setOnClickListener {
            viewModel.click()
        }
    }
}