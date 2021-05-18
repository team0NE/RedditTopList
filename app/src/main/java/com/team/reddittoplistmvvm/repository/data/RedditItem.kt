package com.team.reddittoplistmvvm.repository.data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class RedditItem {
    @SerializedName("data")
    @Expose
    var data: RedditItemData? = null
}