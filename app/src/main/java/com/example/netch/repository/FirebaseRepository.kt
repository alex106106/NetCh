package com.example.netch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.netch.domain.items.feedItems
import com.example.netch.domain.items.toFeedItems
import com.example.netch.remote.feedService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class FirebaseRepository constructor(private val feedService: feedService){
    suspend fun getFeed(): List<feedItems>{
        return feedService.getFeed().map {
            it.toFeedItems()
        }
    }
}