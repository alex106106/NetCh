package com.example.netch.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.collectAsState
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.netch.R
import com.example.netch.remote.models.feedModel
import com.example.netch.ui.viewModel.FeedViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun feedScreen(feedViewModel: FeedViewModel) {

    
    Scaffold(Modifier.background(Color.Black),
        topBar = { topAppBar() },
        content = { contentFeed(feedViewModel) },
        floatingActionButton = { floatingButton(feedViewModel = feedViewModel) }
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
    var Title by remember { mutableStateOf("") }
    var Content by remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }

    fun openDialog() {
        showDialog.value = true
    }

    fun closeDialog() {
        showDialog.value = false
    }
    // El diálogo
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { closeDialog() },
            title = { Text(text = "Add to feed") },
            text = {
                Column {
                    OutlinedTextField(
                        value = Title,
                        onValueChange = { Title = it },
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

                val addFeed by feedViewModel.addFeed.observeAsState()
                Button(
                    onClick = {
                            feedViewModel.add(
                                feed = feedModel(
                                    addFeed?.name ?: "",
                                    addFeed?.title ?: "sasdx",
                                    addFeed?.content ?: "dsax",
                                    addFeed?.feedPostID ?: ""
                                )
                            )
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Blue
                    )
                ) {
                    Text(text = "Post", color = Color.White)
                }
                Button(
                    onClick = { closeDialog() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Red
                    )
                ) {
                    Text(
                        text = "Cerrar",
                        color = Color.White
                    )
                }
            }
        )
    }

    // El botón que abre el diálogo
    FloatingActionButton(onClick = { openDialog() }) {
        Text(text = "ADD")
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

