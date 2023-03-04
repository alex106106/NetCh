package com.example.netch.DAO

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.netch.remote.FeedDao
import com.example.netch.remote.models.feedModel
import com.example.netch.remote.models.registerModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class DAO @Inject constructor() : FeedDao {
    val current = FirebaseAuth.getInstance().currentUser
    val feedsRef = FirebaseDatabase.getInstance().getReference("users/"+current?.uid+"/feed/")
    val otherFriendId = "VHpf0P0umnMIHknbqSA0bScuCH83"
    val otherFriendIdRef = FirebaseDatabase.getInstance().getReference("users/"+current?.uid+"/feed/")

    override fun addFeed(feed: feedModel): LiveData<feedModel> {
        val result = MutableLiveData<feedModel>()
        val current = FirebaseAuth.getInstance().currentUser
        val DBR = FirebaseDatabase.getInstance().getReference("users/"+current?.uid+"/feed/" + UUID.randomUUID())
        val feedModels = feed
        DBR.setValue(feedModels).addOnSuccessListener {
            result.value
        }.addOnFailureListener {
        }
        return result
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

    override suspend fun getFeedFriend(): List<feedModel> {
        val otherUserFeed = mutableListOf<feedModel>()
        val dataSnapshot = otherFriendIdRef.get().await()
        for (feedSnapshot in dataSnapshot.children){
            val OUserFeed = feedSnapshot.getValue(feedModel::class.java)
            otherUserFeed.add(OUserFeed!!)
        }
            return  otherUserFeed
    }

    override fun addFriend(addFriend: registerModel) {
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid ?: return
        fun addFriendToCurrentUser(friendUid: String, friendName: String?, friendEmail: String?) {
            val currentUserRef = FirebaseDatabase.getInstance().getReference("users").child(uid)
            val friendData = mapOf(
                "email" to friendEmail,
                "name" to friendName,

                )
            currentUserRef.child("friends").child(friendUid).setValue(friendData)
        }
        // Si el usuario no está logueado, salir de la función
        val friendRef = FirebaseDatabase.getInstance().getReference("users").child("BhvI2aNXBKZm6l9uVxID231ZhlF3").child("userData")

        friendRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Obtener los datos del usuario amigo
                val friendName = snapshot.child("name").value as? String
                val friendEmail = snapshot.child("email").value as? String
                // Agregar el amigo a la lista de amigos del usuario logueado
                addFriendToCurrentUser("BhvI2aNXBKZm6l9uVxID231ZhlF3", friendName, friendEmail)
            }
            override fun onCancelled(error: DatabaseError) {
                // Manejar errores
            }
        })
    }

}