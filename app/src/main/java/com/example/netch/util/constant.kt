package com.example.netch.util

import androidx.compose.runtime.remember
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class constant {
    companion object{

    }
    object Screens{
        const val LOGIN_SCREEN = "login_screen"
        const val FEED_SCREEN = "feed_screen"
        const val REGISTER_SCREEN = "register_screen"
        const val PROFILE_SCREEN = "profile_screen"
    }
    object const{
        val AUTH = FirebaseAuth.getInstance()
    }

}