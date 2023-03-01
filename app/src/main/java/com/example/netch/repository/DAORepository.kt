package com.example.netch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.netch.DAO.DAO
import com.example.netch.remote.models.feedModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class DAORepository @Inject constructor (val DAO: DAO) {

    suspend fun getFeeds(): List<feedModel> {
        return DAO.getAllFeeds()
    }

     fun addFeed(feed: feedModel): LiveData<feedModel> {
        return DAO.addFeed(feed)
    }

    suspend fun updateFeed(feed: feedModel) {
        DAO.updateFeed(feed)
    }

    suspend fun deleteFeed(feed: feedModel) {
        DAO.deleteFeed(feed.toString())
    }

}
