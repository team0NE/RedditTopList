package com.team.reddittoplistmvvm.repository.data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class TopListData {
    @SerializedName("children")
    @Expose
    var itemsList: List<RedditItem>? = null
}