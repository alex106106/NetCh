package com.example.netch.DAO

import androidx.compose.runtime.mutableStateOf
import com.example.netch.remote.FeedDao
import com.example.netch.remote.models.feedModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DAO @Inject constructor() : FeedDao {
    val current = FirebaseAuth.getInstance().currentUser
    val feedsRef = FirebaseDatabase.getInstance().getReference("users/"+current?.uid+"/feed/")

    override fun addFeed(feed: feedModel) {
        feedsRef.child(feed.feedPostID).setValue(feed)
    }

    override suspend fun getFeed(feedPostID: String): feedModel? {
        var feed: feedModel? = null
        val feedRef = feedsRef.child(feedPostID)
        val dataSnapshot = feedRef.get().await()
        if (dataSnapshot.exists()) {
            feed = dataSnapshot.getValue(feedModel::class.java)
        }
        return feed
    }

    override suspend fun getAllFeeds(): List<feedModel> {
        val feeds = mutableListOf<feedModel>()
        val dataSnapshot = feedsRef.get().await()
        for (feedSnapshot in dataSnapshot.children) {
            val feed = feedSnapshot.getValue(feedModel::class.java)
                feeds.add(feed!!)
        }
        return feeds
    }
    override fun updateFeed(feed: feedModel) {
        feedsRef.child(feed.feedPostID).setValue(feed)

    }

    override fun deleteFeed(feedPostID: String) {
        feedsRef.child(feedPostID).removeValue()

    }

}