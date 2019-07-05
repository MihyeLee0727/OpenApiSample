package com.example.openapisample.presentation.main.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.openapisample.presentation.main.viewmodel.item.TweetItemViewModel

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private val items = arrayListOf<TweetItemViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.create(
            parent
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        items.getOrNull(position)?.let {
            holder.binding?.let { binding ->
                holder.onBind(binding, it, position)
            }
        }
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addAll(source: List<TweetItemViewModel>) {
        val start = items.size
        items.addAll(source)
        notifyItemRangeInserted(start, source.size)
    }

}