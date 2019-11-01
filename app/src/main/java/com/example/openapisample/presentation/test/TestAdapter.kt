package com.example.openapisample.presentation.test

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.openapisample.presentation.main.viewmodel.item.TweetItemViewModel

class NormalAdapter : RecyclerView.Adapter<NormalViewHolder>() {

    private val items = arrayListOf<TweetItemViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalViewHolder {
        return NormalViewHolder.create(parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NormalViewHolder, position: Int) {
        items.getOrNull(position)?.let {
            holder.onBind(holder.binding, it, position)
        }
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun set(source: List<TweetItemViewModel>) {
        items.clear()
        items.addAll(source)
        notifyDataSetChanged()
    }

    fun addAll(source: List<TweetItemViewModel>) {
        val start = items.size
        items.addAll(source)
        notifyItemRangeInserted(start, source.size)
    }
}

class MergedAdapter : RecyclerView.Adapter<MergedViewHolder>() {

    private val items = arrayListOf<TweetItemViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MergedViewHolder {
        return MergedViewHolder.create(
            parent
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MergedViewHolder, position: Int) {
        items.getOrNull(position)?.let {
            holder.onBind(holder.binding, it, position)
        }
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun set(source: List<TweetItemViewModel>) {
        items.clear()
        items.addAll(source)
        notifyDataSetChanged()
    }

    fun addAll(source: List<TweetItemViewModel>) {
        val start = items.size
        items.addAll(source)
        notifyItemRangeInserted(start, source.size)
    }

}

