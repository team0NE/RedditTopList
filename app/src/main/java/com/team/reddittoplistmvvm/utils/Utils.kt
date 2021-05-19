package com.team.reddittoplistmvvm.utils

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.util.Log
import com.team.reddittoplistmvvm.repository.data.RedditItem
import com.team.reddittoplistmvvm.repository.room.RedditItemEntity


class Utils {
    companion object {
        fun isPackageInstalled(packageName: String, packageManager: PackageManager): Boolean {
            return try {
                packageManager.getPackageInfo(packageName, 0)
                true
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }
        }

        fun timePassResolver(timeStamp: Long): String {
            val now = System.currentTimeMillis()/1000
            Log.d("TIME", "inputData is now: $now timeStamp: $timeStamp")
            val diff = now - timeStamp
            Log.d("TIME", "diff: $diff")
            val diffHours = diff / 3600
            val diffDays = diffHours/24
            return if(diffDays > 0) {
                Log.d("TIME", "days: $diffDays")
                "$diffDays days"
            } else {
                Log.d("TIME", "hours: $diffHours")
                "$diffHours hours"
            }
        }

        fun isConnectedToInternet(context: Context): Boolean {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) {
                return true
            }
            return false
        }

        fun convertList(list: List<RedditItem>?): List<RedditItemEntity> {
            var roomList = ArrayList<RedditItemEntity>()

            list?.forEach {
                val newEntity = RedditItemEntity()
                newEntity.author = it.data?.author
                newEntity.score = it.data?.score
                newEntity.createdUtc = it.data?.createdUtc
                newEntity.permalink = it.data?.permalink.toString()
                newEntity.title = it.data?.title
                newEntity.subredditNamePrefixed = it.data?.subredditNamePrefixed
                newEntity.comments = it.data?.comments

                roomList.add(newEntity)
            }

            Log.d("utls", "roomList.size: ${roomList.size}")
            return roomList
        }
    }
}