package com.example.amilosevic.guessthecountry.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amilosevic.guessthecountry.data.User
import com.example.amilosevic.guessthecountry.data.firebase.FirebaseService
import com.google.firebase.auth.FirebaseUser

class RegistrationViewModel(val auth: FirebaseService): ViewModel() {
    private var user = MutableLiveData<User>()

    fun register(email: String, password: String) {
        auth.register(email, password)
    }

    fun login(email: String, password: String) {
        auth.login(email, password)
    }

    fun isSigned(): Boolean {
        return auth.isSigned()
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.getCurrentUser()
    }
}