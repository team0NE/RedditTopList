package com.team.reddittoplistmvvm.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.team.reddittoplistmvvm.App
import com.team.reddittoplistmvvm.repository.api.RedditAPI
import com.team.reddittoplistmvvm.repository.api.RetrofitClient
import com.team.reddittoplistmvvm.repository.data.RedditItem
import com.team.reddittoplistmvvm.repository.room.RedditItemEntity
import com.team.reddittoplistmvvm.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TopListRepository {
    private val _itemList = MutableLiveData<List<RedditItem>>()
    private val itemList: LiveData<List<RedditItem>>  = _itemList
    private val _itemDBList = MutableLiveData<List<RedditItemEntity>>()
    private val itemListDBList: LiveData<List<RedditItemEntity>>  = _itemDBList

    @SuppressLint("CheckResult")
    fun getInternetData() {
        RetrofitClient.getClient()
            .create(RedditAPI::class.java)
            .loadTopList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { resp ->
                val list = resp.data?.itemsList
                _itemList.postValue(list)
            }
    }

    private fun getDBData() {

    }

    fun getInternetList(): LiveData<List<RedditItem>> {
        getInternetData()
        //clear room
        App.database.redditItemDao().deleteAll()
        //save list room
        val roomList = Utils.convertList(itemList.value)
        App.database.redditItemDao().insertAll(roomList)

        return itemList
    }

    fun getDBList(): LiveData<List<RedditItemEntity>> {
        getDBData()
        return itemListDBList
    }
}
