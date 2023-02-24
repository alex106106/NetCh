package com.example.netch.ui.viewModel

import android.content.Context
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.netch.remote.models.registerModel
import com.example.netch.ui.navigation.Screens
import com.example.netch.util.constant.const.AUTH
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel() : ViewModel() {
    fun login(registerModel: registerModel, navController: NavController, context: Context):LiveData<String>{
        val result = MutableLiveData<String>()
        try {
            AUTH.signInWithEmailAndPassword(registerModel.email.toString(),
                registerModel.pass.toString()
            )
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        navController.navigate(Screens.Feed.route)
                    }
                }.addOnFailureListener { e ->
                    result.value = e.message
                }
        } catch (e: Exception){
            result.value = e.message
        }

        return result
    }

    fun navigateToRegister(navController: NavController){
        navController.navigate(Screens.Register.route)
    }
}