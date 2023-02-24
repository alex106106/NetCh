package com.example.netch.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.netch.remote.models.registerModel
import com.example.netch.ui.viewModel.RegisterViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun RegisterScreen(RegisterViewModel: RegisterViewModel, navController: NavController) {

    var emailUser by remember { mutableStateOf("alexisgalindo106@gmail.com") }
    var passUser by remember { mutableStateOf("juniorniko106") }
    var confirmPass by remember { mutableStateOf("juniorniko106") }
    var name by remember { mutableStateOf("niko") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = emailUser,
            onValueChange = { emailUser = it },
            label = { Text("Email") }
        )
        OutlinedTextField(
            value = passUser,
            onValueChange = { passUser = it },
            label = { Text("Password") }
        )
        OutlinedTextField(
            value = confirmPass,
            onValueChange = { confirmPass = it },
            label = { Text("Confirm Password") }
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Button(
            onClick = {
                RegisterViewModel.registerUser(
                    registerModel = registerModel(
                        email = emailUser,
                        pass = passUser,
                        UUID = "",
                        name = name))
            }
        ) {
            Text("Register")
        }
    }

}