package com.example.openapisample.presentation.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.openapisample.R
import com.example.openapisample.databinding.ActivityTestListBinding
import com.example.openapisample.presentation.main.viewmodel.item.TweetItemViewModel

class TestNormalListActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestListBinding
    val adapter = TestNormalAdapter(TestDiffCallback)
//    val adapter = NormalAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_test_list
        )
        binding.list.adapter = adapter
    }
}

object TestDiffCallback : DiffUtil.ItemCallback<TweetItemViewModel>() {
    override fun areItemsTheSame(oldItem: TweetItemViewModel, newItem: TweetItemViewModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: TweetItemViewModel, newItem: TweetItemViewModel): Boolean =
        oldItem == newItem
}

class TestNormalAdapter(callback: DiffUtil.ItemCallback<TweetItemViewModel>) : ListAdapter<TweetItemViewModel, NormalViewHolder>(callback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalViewHolder {
        return NormalViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NormalViewHolder, position: Int) {
        holder.binding?.let { binding ->
            holder.onBind(binding = binding, viewModel = getItem(position), position = position)
        }
    }
}