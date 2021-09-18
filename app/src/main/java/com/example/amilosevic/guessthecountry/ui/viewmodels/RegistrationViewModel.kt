package com.example.amilosevic.guessthecountry.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amilosevic.guessthecountry.data.firebase.FirebaseService
import com.google.firebase.auth.FirebaseUser

class RegistrationViewModel(private val auth: FirebaseService): ViewModel() {

    var currentUser = MutableLiveData<FirebaseUser>()
    var isSignedIn = MutableLiveData<Boolean>()
    var isRegistered = MutableLiveData<Boolean>()

    suspend fun register(email: String, password: String) {

        auth.register(email, password)
        if(auth.isRegistered())
            isRegistered.postValue(true)
    }

    suspend fun login(email: String, password: String) {

        auth.login(email, password)
        if(auth.isSigned()) {
            currentUser.postValue(auth.getCurrentUser())
            isSignedIn.postValue(isSigned())
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