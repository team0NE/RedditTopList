package com.team.reddittoplistmvvm.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.google.android.material.circularreveal.CircularRevealHelper

@Dao
interface RedditItemDao {
    @Query("SELECT * FROM `database`")
    fun getAll(): List<RedditItemEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: ArrayList<RedditItemEntity>)

    @Query("DELETE FROM `database`")
    fun deleteAll()
}