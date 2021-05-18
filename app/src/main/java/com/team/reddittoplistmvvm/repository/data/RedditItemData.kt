package com.team.reddittoplistmvvm.repository.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class RedditItemData {
    @SerializedName("subreddit")
    @Expose
    private var subreddit: String? = null

    @SerializedName("author")
    @Expose
    open var author: String? = null

    @SerializedName("title")
    @Expose
    open var title: String? = null

    @SerializedName("score")
    @Expose
    open var score: Int? = null

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null

    @SerializedName("num_comments")
    @Expose
    var comments: Int? = null

    @SerializedName("permalink")
    @Expose
    open var permalink: String = ""

    @SerializedName("subreddit_name_prefixed")
    @Expose
    open var subredditNamePrefixed: String? = null

    @SerializedName("created_utc")
    @Expose
    open var createdUtc: Long? = null
}
