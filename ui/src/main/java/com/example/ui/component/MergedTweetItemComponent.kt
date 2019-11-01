package com.example.ui.component

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ui.R

class MergedTweetItemComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.component_tweet_merge, this)
    }

//    private val binding: ComponentTweetBinding = DataBindingUtil.inflate(
//        LayoutInflater.from(context),
//        R.layout.component_tweet,
//        this,
//        true
//    )

//    fun setViewModel(viewModel: TweetItemViewModel) {
//        binding.viewModel = viewModel
//    }
}