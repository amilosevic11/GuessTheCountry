package com.example.amilosevic.guessthecountry.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseService(private val auth: FirebaseAuth) {

    var isUserSigned: Boolean = false

    fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                Log.d("LOGIN", "SUCCESSFUL")
            }
            else {
                Log.d("LOGIN", "NOT Successful")
            }
        }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                isUserSigned = true
            }
        }
    }

    fun isSigned(): Boolean {
        return isUserSigned
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}