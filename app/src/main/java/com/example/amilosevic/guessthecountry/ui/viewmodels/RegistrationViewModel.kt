package com.example.amilosevic.guessthecountry.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amilosevic.guessthecountry.data.firebase.FirebaseService
import com.google.firebase.auth.FirebaseUser

class RegistrationViewModel(private val auth: FirebaseService): ViewModel() {

    var currentUser = MutableLiveData<FirebaseUser>()
    var isSignedIn = MutableLiveData<Boolean>()
    var isRegistered = MutableLiveData<Boolean>()

    suspend fun register(email: String, password: String) {
        try {
            auth.register(email, password)
            if(auth.isRegistered())
                isRegistered.postValue(true)
        }
        catch (e: Exception) {
            Log.e("RegistrationException", e.printStackTrace().toString())
        }
    }

    suspend fun login(email: String, password: String) {
        try {
            auth.login(email, password)
            if(auth.isSigned()) {
                currentUser.postValue(auth.getCurrentUser())
                isSignedIn.postValue(isSigned())
            }
        }
        catch (e: Exception) {
            Log.e("LoginException", e.printStackTrace().toString())
        }
    }

    fun signOut() {
        auth.signOut()
    }

    fun isSigned(): Boolean {
        return auth.isSigned()
    }

    fun isRegistered(): Boolean {
        return auth.isRegistered()
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.getCurrentUser()
    }

    fun getCurrentUserId(): String? {
        return auth.getCurrentUser()?.uid
    }
}