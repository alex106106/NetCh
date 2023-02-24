package com.example.netch.remote

import com.example.netch.remote.models.feedModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class feedService constructor(private val feed: feed) {
    suspend fun getFeed(): List<feedModel>{
        return listOf(withContext(Dispatchers.IO){
            val feed = feed.getFeed()
            feed
        })
    }
}