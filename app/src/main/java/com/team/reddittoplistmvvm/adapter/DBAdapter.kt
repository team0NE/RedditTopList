package com.team.reddittoplistmvvm.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.team.reddittoplistmvvm.R
import com.team.reddittoplistmvvm.repository.room.RedditItemEntity
import com.team.reddittoplistmvvm.utils.Utils


class DBAdapter(private val itemList: List<RedditItemEntity>): RecyclerView.Adapter<DBAdapter.ViewHolder>()  {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView? = null
        var author: TextView? = null
        var group: TextView? = null
        var score: TextView? = null
        var time: TextView? = null
        var comments: MaterialButton? = null

        init {
            itemTitle = itemView.findViewById(R.id.item_title)
            author = itemView.findViewById(R.id.item_author)
            group = itemView.findViewById(R.id.item_group)
            comments = itemView.findViewById(R.id.item_comments)
            time = itemView.findViewById(R.id.item_posted_time)
            score = itemView.findViewById(R.id.item_rating)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.top_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle?.text = itemList[position].title
        holder.author?.text = itemList[position].author
        holder.author?.paintFlags = holder.author!!.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        holder.group?.text = itemList[position].subredditNamePrefixed
        holder.group?.paintFlags = holder.author!!.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        holder.comments?.text = itemList[position].comments.toString()
        holder.score?.text = itemList[position].score.toString()
        holder.time?.text = Utils.timePassResolver(itemList[position].createdUtc!!)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}
