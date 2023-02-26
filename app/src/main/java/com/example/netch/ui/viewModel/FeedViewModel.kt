package com.example.netch.ui.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.netch.remote.models.feedModel
import com.example.netch.DAO.DAO
import com.example.netch.repository.DAORepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*

class FeedViewModel  : ViewModel() {

    private val _feedList = MutableStateFlow(emptyList<feedModel>())
    val feedList: StateFlow<List<feedModel>> = _feedList.asStateFlow()

    init{
        viewModelScope.launch {
            val current = FirebaseAuth.getInstance().currentUser
            val firebaseRef = FirebaseDatabase.getInstance().getReference("users/"+current?.uid+"/feed/")
            firebaseRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val feedList = mutableListOf<feedModel>()
                    for (feedSnapshot in snapshot.children) {
                        val feed = feedSnapshot.getValue(feedModel::class.java)
                        feedList.add(feed!!)
                    }
                    _feedList.value = feedList
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

}





































