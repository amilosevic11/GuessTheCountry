package com.example.amilosevic.guessthecountry.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amilosevic.guessthecountry.data.repos.CountriesInfo
import com.example.amilosevic.guessthecountry.data.repos.GuessTheCountryRepository
import com.example.amilosevic.guessthecountry.data.repos.UserRepository
import kotlinx.coroutines.launch

class PlayQuizViewModel(private val repository: GuessTheCountryRepository) : ViewModel() {

    val myResponse : MutableLiveData<ArrayList<CountriesInfo>> = MutableLiveData()

    fun getAllCountries() {
        viewModelScope.launch {
            val response = repository.getAllCountries()
            myResponse.postValue(response)
        }
    }
}