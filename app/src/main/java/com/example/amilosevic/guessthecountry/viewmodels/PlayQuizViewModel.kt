package com.example.amilosevic.guessthecountry.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amilosevic.guessthecountry.data.repos.CountriesInfo
import com.example.amilosevic.guessthecountry.data.repos.GuessTheCountryRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

class PlayQuizViewModel(private val repository: GuessTheCountryRepository) : ViewModel() {

    val myResponse : MutableLiveData<ArrayList<CountriesInfo>> = MutableLiveData()
    private var countriesInfo : ArrayList<CountriesInfo>? = null

    fun getAllCountries() {
        viewModelScope.launch {
            val response = repository.getAllCountries()
            myResponse.postValue(response)
            countriesInfo = response
        }
    }

    fun getRandomCountry() : String? {
        return countriesInfo?.get(Random.nextInt())?.name
    }
}