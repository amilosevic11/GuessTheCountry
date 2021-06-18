package com.example.amilosevic.guessthecountry.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amilosevic.guessthecountry.data.User
import com.example.amilosevic.guessthecountry.data.firebase.FirebaseService
import com.google.firebase.auth.FirebaseUser

class RegistrationViewModel(val auth: FirebaseService): ViewModel() {

    var currentUser = MutableLiveData<FirebaseUser>()
    var isSignedIn = MutableLiveData<Boolean>()

    suspend fun register(email: String, password: String) {
        auth.register(email, password)
    }

    suspend fun login(email: String, password: String) {
        auth.login(email, password)
        currentUser.postValue(getCurrentUser()!!)
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
}