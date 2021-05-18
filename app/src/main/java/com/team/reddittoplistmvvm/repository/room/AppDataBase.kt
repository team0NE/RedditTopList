package com.team.reddittoplistmvvm.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RedditItemEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun redditItemDao(): RedditItemDao
}