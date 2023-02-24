package com.example.netch.app

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class app : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        FirebaseApp.getInstance().persistenceKey
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
