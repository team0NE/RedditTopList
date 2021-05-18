package com.team.reddittoplistmvvm.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RedditItemDao {
    @Query("SELECT * FROM reddititementity")
    fun getAll(): List<RedditItemEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(objects: List<RedditItemEntity>)

    @Query("DELETE FROM reddititementity")
    fun deleteAll()
}