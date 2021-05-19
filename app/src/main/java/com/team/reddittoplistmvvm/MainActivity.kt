package com.team.reddittoplistmvvm

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.team.reddittoplistmvvm.adapter.DBAdapter
import com.team.reddittoplistmvvm.adapter.TopListAdapter
import com.team.reddittoplistmvvm.utils.Utils
import com.team.reddittoplistmvvm.viewmodel.TopListViewModel

class MainActivity: AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private val TAG = MainActivity::class.java.simpleName
    private lateinit var recycler: RecyclerView
    private lateinit var warning: TextView
    private lateinit var refresh: SwipeRefreshLayout
    private val model: TopListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycler_view)
        warning = findViewById(R.id.warning_view)
        refresh = findViewById(R.id.swipeRefreshLayout)
        refresh.setOnRefreshListener(this)

        fetchData()
    }

    private fun fetchData() {
        if (Utils.isConnectedToInternet(this)) {
            Log.d(TAG, "from internet")
            warning.visibility = View.GONE
            if (refresh.isRefreshing) {
                refresh.post{
                    refresh.isRefreshing = false
                }
            }
            val list = model.getData()
            list.observe(this) {
                Log.d(TAG, "listSize: ${it.size}")
                recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                recycler.adapter = TopListAdapter(it, itemClick = { type: String, value: String ->
                    when (type) {
                        "author" -> getAuthorArticles(value)
                        "group" -> getGroupArticles(value)
                        "title" -> openArticle(value)
                        "comments" -> openArticlesComments(value)
                    }
                })
            }
        } else {
            warning.visibility = View.VISIBLE
            Log.d(TAG, "from db")
            val list = model.getDBData()
            list.observe(this) {
                Log.d(TAG, "listSize: ${it.size}")
                recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                recycler.adapter = DBAdapter(it)
            }
        }
    }

    private fun getAuthorArticles(link: String) {
        val url = "http://www.reddit.com/user/${link}/"
        Log.d(TAG, "onItemClick: author: $url")
        openTab(url)
    }

    private fun getGroupArticles(link: String) {
        val url = "http://www.reddit.com/${link}/new"
        openTab(url)
        Log.d(TAG, "onItemClick: group: $url")
    }

    private fun openArticle(link: String) {
        val url = "http://www.reddit.com${link}"
        Log.d(TAG, "onItemClick: title link $url")
        openTab(url)
    }

    private fun openArticlesComments(link: String) {
        val url = "http://www.reddit.com${link}comments"
        Log.d(TAG, "onItemClick: comments link $url")
        openTab(url)
    }

    private fun openTab(url: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        val packageName = "com.android.chrome"
        if (Utils.isPackageInstalled(packageName, this@MainActivity.packageManager)) {
            customTabsIntent.intent.setPackage(packageName)
            customTabsIntent.launchUrl(this, Uri.parse(url))
        } else {
            Log.d(TAG, "packageName Null ")
            Toast.makeText(
                this,
                "Chrome is not installed. Please install chrome to proceed",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onRefresh() {
        refresh.isRefreshing = false
        fetchData()
    }
}