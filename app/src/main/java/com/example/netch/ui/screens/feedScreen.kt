package com.example.netch.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.database.ValueEventListener
import java.util.UUID.randomUUID


@Composable
fun feedScreen(feedViewModel: FeedViewModel) {

    
    Scaffold(Modifier.background(Color.Black),
        topBar = { topAppBar() },
        content = { contentFeed(feedViewModel) },
//        floatingActionButton = { floatingButton(feedViewModel = feedViewModel) }
    )
}

@Composable
fun contentFeed(feedViewModel: FeedViewModel) {

    val feeds by feedViewModel.feedList.collectAsState()

    LazyColumn {
        items(feeds) { feed ->
            Column {
                Card(
                    elevation = 7.dp,
                    shape = RoundedCornerShape(7.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 7.dp, bottom = 7.dp, start = 14.dp, end = 14.dp)

                ) {
                    Row{

                        Column(modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.CenterVertically)) {
                            Text(text = feed.title,
                                fontWeight = FontWeight.Bold)
                            Text(text = feed.content,
                                maxLines = 2, overflow = TextOverflow.Ellipsis)

                        }
                    }
                }
            }
        }
    }
}



@Composable
fun cardPostFeed() {
    Card(
        elevation = 7.dp,
        shape = RoundedCornerShape(7.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 7.dp, bottom = 7.dp, start = 14.dp, end = 14.dp)

    ) {
        Row{
            Image(painter = painterResource(
                id = R.drawable.batdroid),
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .clip(CircleShape))

            Column(modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterVertically)) {
//                Text(text = feed.title.toString(),
//                    fontWeight = FontWeight.Bold)
//                Text(text = feed.content.toString(),
//                    maxLines = 2, overflow = TextOverflow.Ellipsis)

            }
        }
    }
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
}
//    if (showDialog.value) {
//        AlertDialog(
//            onDismissRequest = { closeDialog() },
//            title = { Text(text = "Add to Feed") },
//            text = {
//                Column {
//                    OutlinedTextField(
//                        value = Tile,
//                        onValueChange = { Tile = it },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 8.dp),
//                        label = { Text(text = "Title") }
//                    )
//                    OutlinedTextField(
//                        value = Content,
//                        onValueChange = { Content = it },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 8.dp),
//                        label = { Text(text = "Content") }

//            confirmButton = {
//                Button(onClick = { feedViewModel.addToFeed(
//                    feedModel = feedModel(
//                        title = Tile,
//                        content = Content,
//                        feedPostID = randomUUID().toString(),
//                        name = "")) },
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = Color.Blue
//                )) {
//                    Text(text = "Post", color = Color.White)
//                }
//                Button(
//                    onClick = { closeDialog() },
//                    colors = ButtonDefaults.buttonColors(
//                        backgroundColor = Color.Red
//                    )
//                ) {
//                    Text(
//                        text = "Close",
//                        color = Color.White
//                    )
//                }
//            }
//        )
//    }
//    FloatingActionButton(backgroundColor = Color.Black
//        ,onClick = { openDialog() }) {
//        Text(text = "asd")
//    }

