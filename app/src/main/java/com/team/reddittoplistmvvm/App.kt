package com.team.reddittoplistmvvm

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.team.reddittoplistmvvm.repository.room.AppDatabase


open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        lateinit var database: AppDatabase
        lateinit var Instance: App
        fun getContext(): Context {
            return Instance.applicationContext
        }

        fun getAppDataBase(): AppDatabase {
            return database
        }
    }
}