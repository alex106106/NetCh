package com.example.netch.ui.navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.netch.ui.screens.RegisterScreen
import com.example.netch.ui.screens.feedScreen
import com.example.netch.ui.screens.loginScreen
import com.example.netch.ui.screens.profileScreen
import com.example.netch.ui.viewModel.FeedViewModel
import com.example.netch.ui.viewModel.LoginViewModel
import com.example.netch.ui.viewModel.RegisterViewModel
import com.example.netch.util.constant.Screens.FEED_SCREEN
import com.example.netch.util.constant.Screens.LOGIN_SCREEN
import com.example.netch.util.constant.Screens.PROFILE_SCREEN
import com.example.netch.util.constant.Screens.REGISTER_SCREEN

sealed class Screens(val route: String) {
    object Login: Screens(route = LOGIN_SCREEN)
    object Feed: Screens(route = FEED_SCREEN)
    object Register: Screens(route = REGISTER_SCREEN)
    object Profile: Screens(route = PROFILE_SCREEN)
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SetupNavHost(
    navHostController: NavHostController,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    feedViewModel: FeedViewModel,
    context: Context) {
    NavHost(navController = navHostController, startDestination = Screens.Login.route){
        composable(route = Screens.Login.route){
            loginScreen(loginViewModel = loginViewModel, navController = navHostController, context = context)
        }
        composable(route = Screens.Feed.route){
            feedScreen(feedViewModel = feedViewModel, navController = navHostController)
        }
        composable(route = Screens.Register.route){
            RegisterScreen(RegisterViewModel = registerViewModel, navController = navHostController)
        }
        composable(route = Screens.Profile.route){
            profileScreen(navController = navHostController)
        }
    }
}
