package com.example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.component_tweet_merge.view.*

class TestMergedListActivity : AppCompatActivity() {
    lateinit var list: RecyclerView
    val adapter = TestMergedAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_list)

        list = findViewById(R.id.list)
        list.adapter = adapter
    }
}

class TestMergedAdapter : ListAdapter<TweetItemViewModel, MergedViewHolder>(TestDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MergedViewHolder {
        return MergedViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MergedViewHolder, position: Int) {
        holder.onBind(viewModel = getItem(position))
    }
}

class MergedViewHolder(
    private val containerView: View
) : RecyclerView.ViewHolder(containerView) {

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

    fun onBind(viewModel: TweetItemViewModel) {
        containerView.profile.setRemoteSrc(viewModel.userProfileImageUrl, true)
        containerView.userName.text = viewModel.userName
        containerView.image.setRemoteSrc(viewModel.imageUrl, true)
        containerView.userName.text = viewModel.contents
        containerView.createdAt.text = viewModel.createdAt
        containerView.retweetCount.setTextFromNumber(viewModel.retweetCount)
        containerView.favoriteCount.setTextFromNumber(viewModel.favoriteCount)
        containerView.favoriteIcon.setShow(viewModel.favoriteCount > 0)
        containerView.favoriteCount.setShow(viewModel.favoriteCount > 0)
        containerView.retweetIcon.setShow(viewModel.retweetCount > 0)
        containerView.image.setShow(viewModel.hasImage)
    }
}