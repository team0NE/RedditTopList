package com.team.reddittoplistmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.team.reddittoplistmvvm.repository.TopListRepository
import com.team.reddittoplistmvvm.repository.data.RedditItem
import com.team.reddittoplistmvvm.repository.room.RedditItemEntity

class TopListViewModel: ViewModel() {
    private val topListRepository = TopListRepository()

    fun getData(): LiveData<List<RedditItem>> {
        return topListRepository.getInternetList()
    }

    fun getDBData(): LiveData<List<RedditItemEntity>> {
        return topListRepository.getDBList()
    }
}