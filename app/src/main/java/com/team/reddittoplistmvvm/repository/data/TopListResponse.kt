package com.team.reddittoplistmvvm.repository.data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class TopListResponse {
    @SerializedName("data")
    @Expose
    var data: TopListData? = null
}