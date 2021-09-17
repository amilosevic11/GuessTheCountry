package com.example.amilosevic.guessthecountry.di

import com.example.amilosevic.guessthecountry.data.firebase.CloudStorage
import com.example.amilosevic.guessthecountry.model.ResultDetails
import com.example.amilosevic.guessthecountry.data.firebase.FirebaseService
import com.example.amilosevic.guessthecountry.data.repos.GuessTheCountryRepository
import com.example.amilosevic.guessthecountry.ui.viewmodels.PlayOrSeeResultsViewModel
import com.example.amilosevic.guessthecountry.ui.viewmodels.PlayQuizViewModel
import com.example.amilosevic.guessthecountry.ui.viewmodels.RegistrationViewModel
import com.example.amilosevic.guessthecountry.ui.viewmodels.SeeResultsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { ResultDetails() }
}

val viewModelModule = module {
    viewModel { RegistrationViewModel(FirebaseService(FirebaseAuth.getInstance())) }
    viewModel { PlayOrSeeResultsViewModel(CloudStorage(FirebaseStorage.getInstance())) }
    viewModel { PlayQuizViewModel(GuessTheCountryRepository()) }
    viewModel { SeeResultsViewModel(FirebaseFirestore.getInstance(), CloudStorage(FirebaseStorage.getInstance())) }
}