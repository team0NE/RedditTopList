package com.team.reddittoplistmvvm.repository.api

import com.team.reddittoplistmvvm.repository.data.TopListResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface RedditAPI {
    @GET("top.json?limit=50")
    fun loadTopList(): Observable<TopListResponse>
}