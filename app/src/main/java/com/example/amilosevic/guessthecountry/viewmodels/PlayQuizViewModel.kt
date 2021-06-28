package com.example.amilosevic.guessthecountry.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amilosevic.guessthecountry.data.repos.CountriesInfo
import com.example.amilosevic.guessthecountry.data.repos.GuessTheCountryRepository
import com.example.amilosevic.guessthecountry.utils.Constants.Companion.COUNTRY_FLAGS_URL
import com.example.amilosevic.guessthecountry.utils.Constants.Companion.FLAT_64
import kotlinx.coroutines.launch
import kotlin.random.Random

class PlayQuizViewModel(private val repository: GuessTheCountryRepository) : ViewModel() {

    val myResponse : MutableLiveData<ArrayList<CountriesInfo>> = MutableLiveData()
    private var countriesInfo : ArrayList<CountriesInfo>? = null
    val currentImage : MutableLiveData<String> = MutableLiveData()

    private var imageNumber: Int = 0

    fun getAllCountries() {
        viewModelScope.launch {
            val response = repository.getAllCountries()
            myResponse.postValue(response)
            countriesInfo = response
            setRandomCountryFlag()
        }
    }

    fun setRandomCountryFlag() {
        imageNumber = Random.nextInt(countriesInfo!!.size)
        currentImage.postValue(COUNTRY_FLAGS_URL + countriesInfo?.get(imageNumber)?.alpha2Code + FLAT_64)
    }

}