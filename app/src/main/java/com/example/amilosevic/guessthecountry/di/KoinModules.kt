package com.example.amilosevic.guessthecountry.di

import com.example.amilosevic.guessthecountry.data.firebase.CloudStorage
import com.example.amilosevic.guessthecountry.data.repos.UserRepository
import com.example.amilosevic.guessthecountry.data.firebase.FirebaseService
import com.example.amilosevic.guessthecountry.viewmodels.PlayOrSeeResultsViewModel
import com.example.amilosevic.guessthecountry.viewmodels.PlayQuizViewModel
import com.example.amilosevic.guessthecountry.viewmodels.RegistrationViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { UserRepository() }
}

val viewModelModule = module {
    viewModel { RegistrationViewModel(FirebaseService(FirebaseAuth.getInstance())) }
//    viewModel { PlayOrSeeResultsViewModel(FirestoreDatabase(FirebaseFirestore.getInstance()))}
    viewModel { PlayOrSeeResultsViewModel(CloudStorage(FirebaseStorage.getInstance()))}
    viewModel { PlayQuizViewModel(get()) }
}