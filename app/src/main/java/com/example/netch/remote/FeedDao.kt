package com.example.netch.remote

import com.example.netch.remote.models.feedModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface FeedDao {
    fun addFeed(feed: feedModel)
    suspend fun getFeed(feedPostID: String): feedModel?
    suspend fun getAllFeeds(): List<feedModel>
    fun updateFeed(feed: feedModel)
    fun deleteFeed(feedPostID: String)
}