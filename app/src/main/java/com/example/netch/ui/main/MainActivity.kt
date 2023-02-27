package com.example.netch.ui.main

import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.netch.ui.navigation.SetupNavHost
import com.example.netch.ui.theme.NetChTheme
import com.example.netch.ui.viewModel.FeedViewModel
import com.example.netch.ui.viewModel.LoginViewModel
import com.example.netch.ui.viewModel.RegisterViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<RegisterViewModel>()
        val viewModelLogin by viewModels<LoginViewModel>()
        val FeedVM by viewModels<FeedViewModel>()
        setContent {
            NetChTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    SetupNavHost(navHostController = navController,
                        loginViewModel = viewModelLogin,
                        registerViewModel = viewModel,
                        context = this,
                        feedViewModel = FeedVM)
                }
            }
        }
    }
}

