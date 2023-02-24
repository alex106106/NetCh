package com.example.netch.ui.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netch.domain.getFeedUseCase
import com.example.netch.domain.items.feedItems
import com.example.netch.remote.models.feedModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch
import java.util.*

class FeedViewModel constructor(private val getFeedUseCase: getFeedUseCase): ViewModel() {

    private val _postFeed = MutableLiveData<List<feedItems>>()
    val postFeed: LiveData<List<feedItems>>get() = _postFeed
    init {
        getPostFeed()
    }

    private fun getPostFeed() {
        viewModelScope.launch {
            try {
                val post = getFeedUseCase()
                _postFeed.value = post
            }catch (e: Exception){}
        }
    }



    private val _name = mutableStateOf("")
    val name: State<String> = _name
    fun addToFeed(feedModel: feedModel){
        var sd = ""
        val current = FirebaseAuth.getInstance().currentUser
        val DBR = FirebaseDatabase.getInstance().getReference("users/"+current?.uid+"/feed/" + UUID.randomUUID())
        val feedModel = feedModel
        DBR.setValue(feedModel)
    }

}