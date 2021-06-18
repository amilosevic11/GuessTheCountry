package com.example.amilosevic.guessthecountry.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseService(private val auth: FirebaseAuth) {

    private var isUserSigned: Boolean = false
    private var isUserRegistered: Boolean = false

    suspend fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                isUserRegistered = true
                Log.d("LOGIN", "SUCCESSFUL")
            }
            else {
                Log.d("LOGIN", "NOT Successful")
            }
        }
    }

    suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                isUserSigned = true
            }
        }
    }

    fun isSigned(): Boolean {
        return isUserSigned
    }

    fun isRegistered(): Boolean {
        return isUserRegistered
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}