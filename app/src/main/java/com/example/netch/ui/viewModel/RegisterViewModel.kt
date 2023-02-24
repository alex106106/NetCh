package com.example.netch.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.netch.remote.models.registerModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RegisterViewModel : ViewModel() {
    fun registerUser(registerModel: registerModel): LiveData<String>{
            val auth = FirebaseAuth.getInstance()
            val result = MutableLiveData<String>()
            auth.createUserWithEmailAndPassword(registerModel.email.toString(),
                registerModel.pass.toString()
            )
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val current = FirebaseAuth.getInstance().currentUser
                        val DBR = FirebaseDatabase.getInstance().getReference("users/"+current?.uid)
                        val registerModel = registerModel
                        registerModel.UUID.plus(current?.uid)
                        DBR.setValue(registerModel)
                        result.value = "success"
                    } else {
                        result.value = "error"
                    }
                }
            return result
    }
}