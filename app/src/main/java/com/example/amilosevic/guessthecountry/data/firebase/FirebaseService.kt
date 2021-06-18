package com.example.amilosevic.guessthecountry.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseService(private val auth: FirebaseAuth) {

    private var isUserSigned: Boolean = false
    private var isUserRegistered: Boolean = false


    suspend fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                isUserRegistered = true
                Log.d("REGISTRATION -->", "SUCCESSFUL")
            }
            else {
                Log.d("LOGIN -->", "NOT Successful")
            }
        }.addOnFailureListener {
            Log.e("Registration error --->", it.toString())
        }.await()
    }

    suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                Log.e("Login success --->", it.toString())
            }
        }.addOnFailureListener {
            Log.e("Login error --->", it.toString())
        }.await()
    }

    fun signOut() {
        auth.signOut()
    }

    fun isSigned(): Boolean {
        return auth.currentUser != null
    }

    fun isRegistered(): Boolean {
        return isUserRegistered
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}