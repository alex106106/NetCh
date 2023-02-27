package com.example.netch.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.netch.DAO.DAO
import com.example.netch.remote.models.feedModel
import com.example.netch.repository.DAORepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val DAO: DAORepository) : ViewModel() {

    private val _feedList = MutableStateFlow(emptyList<feedModel>())
    val feedList: StateFlow<List<feedModel>> = _feedList.asStateFlow()
    init {
        viewModelScope.launch {
            val feedList = DAO.getFeeds()
            _feedList.value = feedList
        }
    }
}





































//private val _feedList = MutableStateFlow(emptyList<feedModel>())
//val feedList: StateFlow<List<feedModel>> = _feedList.asStateFlow()
//
//init {
//    viewModelScope.launch {
//        val current = FirebaseAuth.getInstance().currentUser
//        val firebaseRef = FirebaseDatabase.getInstance().getReference("users/"+current?.uid+"/feed/")
//        firebaseRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val feedList = mutableListOf<feedModel>()
//                for (feedSnapshot in snapshot.children) {
//                    val feed = feedSnapshot.getValue(feedModel::class.java)
//                    feedList.add(feed!!)
//                }
//                _feedList.value = feedList
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Handle error
//            }
//        })
//    }
//}