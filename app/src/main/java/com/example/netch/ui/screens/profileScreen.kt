package com.example.netch.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.netch.R

@Composable
fun profileScreen(navController: NavController) {

    var isExpanded by remember { mutableStateOf(false) }

    val showDialog = remember { mutableStateOf(false) }

    fun openDialog() {
        showDialog.value = true
    }

    fun closeDialog() {
        showDialog.value = false
    }
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState){
        item {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                // El diálogo
                if (showDialog.value) {
                    AlertDialog(
                        onDismissRequest = { closeDialog() },
                        confirmButton = {
                            if (isExpanded){

                                Image(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            isExpanded = false
                                            closeDialog()
                                        }
                                        .size(350.dp),
                                    alignment = Alignment.Center,
                                    contentDescription = "Profile Image",
                                    painter = painterResource(id = R.drawable.batdroid)
                                )

                            }
                        }
                    )
                }

                Card(
                    elevation = 7.dp,
                    shape = RoundedCornerShape(7.dp),
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column() {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Bottom
                        ) {

                            Box(
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(
                                            if (scrollState.firstVisibleItemIndex == 0) {
                                                200.dp - (scrollState.firstVisibleItemScrollOffset.toFloat().dp / 2)
                                            } else {
                                                60.dp
                                            }
                                        )
                                        .clip(CircleShape)
                                        .border(
                                            width = 4.dp,
                                            color = Color.White,
                                            shape = CircleShape
                                        )
                                        .clickable {
                                            openDialog()
                                            isExpanded = true
                                        },
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "Profile Image",
                                    painter = painterResource(id = R.drawable.batdroid)
                                )
                            }
                        }
                        Text(
                            text = "Name",
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.Start))
                        Text(text = "Description",
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.Start))
                        Row() {
                            Button(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxSize()
                                    .weight(1f),
                                onClick = { /*TODO*/ }) {
                                Text(text = "TODO")
                            }
                            Button(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .fillMaxSize()
                                    .weight(1f),
                                onClick = { /*TODO*/ }) {
                                Text(text = "TODO")
                            }
                        }
                    }
                }

                Card(
                    elevation = 7.dp,
                    shape = RoundedCornerShape(7.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 7.dp, bottom = 7.dp, start = 5.dp, end = 5.dp)
                ) {
                    Column() {
                        Text(text = "Details",
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.Start))
                        Text(text = "Day of birth",
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.Start))
                        Text(text = "From",
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.Start))
                        Text(text = "Email",
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.Start))
                    }
                }

                Card(
                    elevation = 7.dp,
                    shape = RoundedCornerShape(7.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 7.dp, bottom = 7.dp, start = 5.dp, end = 5.dp)
                ) {
                    Text(text = "Friends",
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.Start))
                    Column() {
                        Row() {
                            repeat(3){
                                IconButton(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(5.dp),
                                    onClick = { }) {
                                    Icon(
                                        painter = painterResource(
                                            id = R.drawable.profile),
                                        contentDescription = "null")
                                }
                            }
                        }
                        Row() {
                            repeat(3){
                                IconButton(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(5.dp),
                                    onClick = { /*TODO*/ }) {
                                    Icon(
                                        painter = painterResource(
                                            id = R.drawable.profile),
                                        contentDescription = "null")

                                }
                            }
                        }
                        Row() {
                            repeat(3){
                                IconButton(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(5.dp),
                                    onClick = { }) {
                                    Icon(
                                        painter = painterResource(
                                            id = R.drawable.profile),
                                        contentDescription = "null")
                                }
                            }
                        }
                        Row() {
                            repeat(3){
                                IconButton(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(5.dp),
                                    onClick = { /*TODO*/ }) {
                                    Icon(
                                        painter = painterResource(
                                            id = R.drawable.profile),
                                        contentDescription = "null")

                                }
                            }
                        }
                        Row() {
                            repeat(3){
                                IconButton(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(5.dp),
                                    onClick = { /*TODO*/ }) {
                                    Icon(
                                        painter = painterResource(
                                            id = R.drawable.profile),
                                        contentDescription = "null")

                                }
                            }
                        }
                    }
                }
            }
        }

    }

}