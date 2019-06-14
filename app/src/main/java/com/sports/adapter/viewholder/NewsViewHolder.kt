package com.sports.adapter.viewholder

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sports.R
import com.sports.SwipeActivity
import com.sports.model.News
import com.sports.utils.GlideApp
import kotlinx.android.synthetic.main.item_news.view.*

class NewsViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)) {
    private lateinit var data: News
    fun setData(data: News) {
        this.data = data
        itemView.title.text = data.title
        GlideApp.with(itemView.image)
            .load("https://www.chinadailyhk.com${data.imageUrl}")
            .centerCrop()
            .into(itemView.image)
    }

    init {
        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context, SwipeActivity::class.java))
        }
    }
}