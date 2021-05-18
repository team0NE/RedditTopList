package com.team.reddittoplistmvvm.repository.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reddititementity")
class RedditItemEntity {
    @PrimaryKey
    @NonNull
    var permalink: String = ""
    var author: String? = null
    var title: String? = null
    var subredditNamePrefixed: String? = null
    var score: Int? = 0
    var createdUtc: Long? = 0
    var comments: Int? = null
}