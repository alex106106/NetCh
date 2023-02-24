package com.example.netch.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.*
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.netch.R
import com.example.netch.remote.models.feedModel
import com.example.netch.ui.viewModel.FeedViewModel
import com.example.netch.util.constant
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import androidx.lifecycle.LiveData
import com.google.firebase.database.ValueEventListener
import java.util.UUID.randomUUID


@Composable
fun feedScreen(feedViewModel: FeedViewModel) {

    
    Scaffold(Modifier.background(Color.Black),
        topBar = { topAppBar() },
        content = { contentFeed(feedViewModel = feedViewModel) },
        floatingActionButton = { floatingButton(feedViewModel = feedViewModel) }
    )
}

@Composable
fun contentFeed(feedViewModel: FeedViewModel) {
//    val feed = feedViewModel.postFeed.ob(listOf()).value
    var name by remember { mutableStateOf("") }
    fun sa(){
        val cu = FirebaseAuth.getInstance().currentUser
        try {
            val ref = FirebaseDatabase.getInstance().getReference("users/"+cu?.uid)
            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val re = FirebaseDatabase.getInstance().getReference("users/"+cu?.uid)
                    re.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            try {
                                var feedModel = snapshot.getValue(com.example.netch.remote.models.feedModel::class.java)!!
                                constant.const.NAME = feedModel.name.toString()
                                name = constant.const.NAME
                            }catch (e: Exception){}
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }

                    })

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }catch (e: Exception){}
    }
    sa()

}



@Composable
fun cardPostFeed() {

}

@Composable
fun topAppBar() {
    TopAppBar(
        title = { Text(text = "App")},
        elevation = 4.dp,
        actions = {
            Image(painter = painterResource(
                id = R.drawable.batdroid),
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .clip(CircleShape))
        }

    )
}


@Composable
fun floatingButton(feedViewModel: FeedViewModel) {
    var Tile by remember { mutableStateOf("") }
    var Content by remember { mutableStateOf("") }
    var feedPostID = ""

    val showDialog = remember { mutableStateOf(false) }

    fun openDialog() {
        showDialog.value = true
    }

    fun closeDialog() {
        showDialog.value = false
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { closeDialog() },
            title = { Text(text = "Add to Feed") },
            text = {
                Column {
                    OutlinedTextField(
                        value = Tile,
                        onValueChange = { Tile = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        label = { Text(text = "Title") }
                    )
                    OutlinedTextField(
                        value = Content,
                        onValueChange = { Content = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        label = { Text(text = "Content") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = { feedViewModel.addToFeed(
                    feedModel = feedModel(
                        title = Tile,
                        content = Content,
                        feedPostID = randomUUID().toString())) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue
                )) {
                    Text(text = "Post", color = Color.White)
                }
                Button(
                    onClick = { closeDialog() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Red
                    )
                ) {
                    Text(
                        text = "Close",
                        color = Color.White
                    )
                }
            }
        )
    }
    FloatingActionButton(backgroundColor = Color.Black
        ,onClick = { openDialog() }) {
        Text(text = "asd")
    }

}
