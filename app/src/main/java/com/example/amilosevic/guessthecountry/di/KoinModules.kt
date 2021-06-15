package com.example.amilosevic.guessthecountry.di

import com.example.amilosevic.guessthecountry.model.User
import com.example.amilosevic.guessthecountry.data.firebase.FirebaseService
import com.example.amilosevic.guessthecountry.viewmodels.RegistrationViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { User() }
}

val viewModelModule = module {
    viewModel { RegistrationViewModel(FirebaseService(FirebaseAuth.getInstance())) }
}