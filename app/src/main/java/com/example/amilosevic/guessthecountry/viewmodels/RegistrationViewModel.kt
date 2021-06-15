package com.example.amilosevic.guessthecountry.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amilosevic.guessthecountry.data.firebase.FirebaseService
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class RegistrationViewModel(private val auth: FirebaseService): ViewModel() {

    //var currentUser: MutableLiveData<FirebaseUser> = MutableLiveData()
    var currentUser = MutableLiveData<FirebaseUser>()
    var isSignedIn = MutableLiveData<Boolean>()
    var isRegistered = MutableLiveData<Boolean>()

    fun register(email: String, password: String) {
        viewModelScope.launch {
            auth.register(email, password)
        }
        if(auth.isRegistered())
            isRegistered.postValue(true)
    }

    fun login(email: String, password: String) {
        auth.login(email, password)

        if(auth.isSigned()) {
            currentUser.postValue(auth.getCurrentUser())
            isSignedIn.postValue(true)
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
}