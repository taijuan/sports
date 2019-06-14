package com.sports.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sports.adapter.viewholder.NewsViewHolder
import com.sports.model.News

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data = mutableListOf<News>()
    fun refresh(data: List<News>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun loadMore(data: List<News>) {
        val position = data.size
        this.data.addAll(data)
        notifyItemInserted(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(parent)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = this.data[position]
        if (holder is NewsViewHolder) {
            holder.setData(data)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

}