package com.example.netch.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.netch.remote.models.registerModel
import com.example.netch.ui.viewModel.LoginViewModel

@Composable
fun loginScreen(loginViewModel: LoginViewModel, navController: NavController, context: Context) {
    var emailUser by remember { mutableStateOf("alexisgalindo106@gmail.com") }
    var passUser by remember { mutableStateOf("juniorniko106") }
    var confirmPass by remember { mutableStateOf("juniorniko106") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.padding(15.dp),
            value = emailUser,
            onValueChange = { emailUser = it },
            label = { Text("Email") }
        )
        OutlinedTextField(
            modifier = Modifier.padding(15.dp),
            value = passUser,
            onValueChange = { passUser = it },
            label = { Text("Password") }
        )
        OutlinedTextField(
            modifier = Modifier.padding(15.dp),
            value = confirmPass,
            onValueChange = { confirmPass = it },
            label = { Text("Confirm Password") }
        )
        Button(
            modifier = Modifier.padding(15.dp),
            onClick = {
                loginViewModel.login(

                    registerModel = registerModel(
                        email = emailUser,
                        pass = passUser, name = "", UUID = ""),
                    navController = navController,
                    context = context
                )
            }
        ) {
            Text("Login")
        }
        Button(
            modifier = Modifier.padding(5.dp),
            onClick = {
            loginViewModel.navigateToRegister(navController) 
        }) {
            Text(text = "Register")
        }
    }
}