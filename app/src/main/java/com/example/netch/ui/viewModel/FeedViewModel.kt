package com.example.netch.ui.viewModel

import androidx.lifecycle.*
import com.example.netch.remote.models.feedModel
import com.example.netch.remote.models.registerModel
import com.example.netch.repository.DAORepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

    private val _addFeed = MutableLiveData<feedModel>()
    val addFeed: LiveData<feedModel>  = _addFeed


//    private val _otherUserFeed = MutableStateFlow(emptyList<feedModel>())
//    val otherUserFeed = StateFlow<List<feedModel>> = _otherUserFeed.asStateFlow()


    init {
        viewModelScope.launch {
            val feedList = DAO.getFeeds()
            _feedList.value = feedList
        }
    }


    fun add(feed: feedModel) {
        viewModelScope.launch {
            val add = DAO.addFeed(feed)
            _addFeed.value = add.value
        }
    }

    fun addFriend(addFriend: registerModel){
        viewModelScope.launch {
            val add = DAO.addFriend(addFriend)
            add
        }
    }

//    fun addF(){
//        val user = FirebaseAuth.getInstance().currentUser
//        val uid = user?.uid ?: return
//        fun addFriendToCurrentUser(friendUid: String, friendName: String?, friendEmail: String?) {
//            val currentUserRef = FirebaseDatabase.getInstance().getReference("users").child(uid)
//            val friendData = mapOf(
//                "email" to friendEmail,
//                "name" to friendName,
//
//            )
//            currentUserRef.child("friends").child(friendUid).setValue(friendData)
//        }
//       // Si el usuario no está logueado, salir de la función
//        val friendRef = FirebaseDatabase.getInstance().getReference("users").child("VHpf0P0umnMIHknbqSA0bScuCH83")
//        friendRef.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                // Obtener los datos del usuario amigo
//                val friendName = snapshot.child("email").value as? String
//                val friendEmail = snapshot.child("name").value as? String
//                // Agregar el amigo a la lista de amigos del usuario logueado
//                addFriendToCurrentUser("VHpf0P0umnMIHknbqSA0bScuCH83", friendName, friendEmail)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Manejar errores
//            }
//        })
//
//
//
//
//
//    }


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