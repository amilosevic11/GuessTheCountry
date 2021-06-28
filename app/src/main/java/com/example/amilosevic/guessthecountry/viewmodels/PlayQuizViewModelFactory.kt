package com.example.amilosevic.guessthecountry.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.amilosevic.guessthecountry.data.repos.GuessTheCountryRepository

class PlayQuizViewModelFactory(private val repository: GuessTheCountryRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayQuizViewModel(repository) as T
    }
}