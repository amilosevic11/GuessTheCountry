package com.example.amilosevic.guessthecountry.di

import com.example.amilosevic.guessthecountry.data.firebase.FirebaseRealtimeDatabase
import com.example.amilosevic.guessthecountry.model.User
import com.example.amilosevic.guessthecountry.data.firebase.FirebaseService
import com.example.amilosevic.guessthecountry.viewmodels.PlayOrSeeResultsViewModel
import com.example.amilosevic.guessthecountry.viewmodels.RegistrationViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { User() }
}

val viewModelModule = module {
    viewModel { RegistrationViewModel(FirebaseService(FirebaseAuth.getInstance())) }
    viewModel { PlayOrSeeResultsViewModel(FirebaseRealtimeDatabase(FirebaseDatabase.getInstance()))}
}