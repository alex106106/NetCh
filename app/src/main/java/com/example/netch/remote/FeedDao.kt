package com.example.netch.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.netch.remote.models.feedModel
import com.example.netch.remote.models.registerModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface FeedDao {
    fun addFeed(feed: feedModel): LiveData<feedModel>
    suspend fun getFeed(feedPostID: String): feedModel?
    suspend fun getAllFeeds(): List<feedModel>
    fun updateFeed(feed: feedModel)
    fun deleteFeed(feedPostID: String)
    suspend fun getFeedFriend(): List<feedModel>
    fun addFriend(addFriend: registerModel)
}
