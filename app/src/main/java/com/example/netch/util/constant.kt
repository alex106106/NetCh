package com.example.netch.util

import androidx.compose.runtime.remember
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class constant {
    companion object{
        const val ERROR = ""
        const val SUCCESS = ""
    }
    object Screens{
        const val LOGIN_SCREEN = "login_screen"
        const val FEED_SCREEN = "feed_screen"
        const val REGISTER_SCREEN = "register_screen"
    }
    object const{
        val AUTH = FirebaseAuth.getInstance()
        var NAME: String = ""
        var NAME_USER: String = ""
        var prueba: String = ""

    }

}