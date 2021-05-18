package com.team.reddittoplistmvvm.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.team.reddittoplistmvvm.R
import com.team.reddittoplistmvvm.repository.data.RedditItem
import com.team.reddittoplistmvvm.utils.Utils


class TopListAdapter(private val itemList: List<RedditItem>,
                     private val itemClick: (type: String, value: String) -> Unit
                    ): RecyclerView.Adapter<TopListAdapter.ViewHolder>()  {
    class ViewHolder(itemView: View,
                     private val itemClick: (type: String, value: String) -> Unit
                    ) : RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView? = null
        var author: TextView? = null
        var group: TextView? = null
        var score: TextView? = null
        var time: TextView? = null
        var comments: MaterialButton? = null
        var thumbnail: ImageView? = null

        init {
            itemTitle = itemView.findViewById(R.id.item_title)
            author = itemView.findViewById(R.id.item_author)
            group = itemView.findViewById(R.id.item_group)
            comments = itemView.findViewById(R.id.item_comments)
            time = itemView.findViewById(R.id.item_posted_time)
            score = itemView.findViewById(R.id.item_rating)
            thumbnail = itemView.findViewById(R.id.item_thumbnail)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.top_item, parent, false)
        return ViewHolder(itemView, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle?.text = itemList[position].data?.title
        holder.itemTitle?.setOnClickListener {
            itemClick("title", itemList[position].data?.permalink!!)
        }
        holder.author?.text = itemList[position].data?.author
        holder.author?.paintFlags = holder.author!!.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        holder.author?.setOnClickListener {
            itemClick("author", itemList[position].data?.author!!)
        }
        holder.group?.text = itemList[position].data?.subredditNamePrefixed
        holder.group?.setOnClickListener {
            itemClick("group", itemList[position].data?.subredditNamePrefixed!!)
        }
        holder.group?.paintFlags = holder.author!!.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        holder.comments?.text = itemList[position].data?.comments.toString()
        holder.comments?.setOnClickListener {
            itemClick("comments", itemList[position].data?.permalink!!)
        }
        holder.score?.text = itemList[position].data?.score.toString()
        holder.time?.text = Utils.timePassResolver(itemList[position].data?.createdUtc!!)
        val itemThumbnail = itemList[position].data?.thumbnail
        if (itemThumbnail != null) {
            Glide
                .with(holder.itemView.context)
                .load(itemThumbnail)
                .override(500, 500)
                .fitCenter()
                .into(holder.thumbnail!!)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}
