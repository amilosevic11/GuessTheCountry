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
    var countriesInfo : ArrayList<CountriesInfo>? = null
    val currentImage : MutableLiveData<String> = MutableLiveData()
    var isAnswerCorrect : MutableLiveData<Boolean> = MutableLiveData()
    var currentQuestionFlag : MutableLiveData<Int> = MutableLiveData()
    var currentQuestion: Int = 0

    var currentImageName: String = ""
    var currentImageNumber: Int = 0
    var currentFirstCountry: Int = 0
    var currentSecondCountry: Int = 0
    var currentThirdCountry: Int = 0
    var previousCountryNumbers: ArrayList<Int>? = null


    fun getAllCountries() {
        viewModelScope.launch {
            val response = repository.getAllCountries()
            myResponse.postValue(response)
            countriesInfo = response

            setRandomCountries()
        }
    }

    fun setRandomCountries() {
        var rndNum = Random.nextInt(countriesInfo!!.size)

        while(previousCountryNumbers?.contains(rndNum) == true) {
            rndNum = Random.nextInt(countriesInfo!!.size)
        }
        previousCountryNumbers?.add(rndNum)
        currentFirstCountry = rndNum

        rndNum = Random.nextInt(countriesInfo!!.size)
        while(previousCountryNumbers?.contains(rndNum) == true) {
            rndNum = Random.nextInt(countriesInfo!!.size)
        }
        previousCountryNumbers?.add(rndNum)
        currentSecondCountry = rndNum

        rndNum = Random.nextInt(countriesInfo!!.size)
        while(previousCountryNumbers?.contains(rndNum) == true) {
            rndNum = Random.nextInt(countriesInfo!!.size)
        }
        previousCountryNumbers?.add(rndNum)
        currentThirdCountry = rndNum

        rndNum = Random.nextInt(countriesInfo!!.size)
        while(previousCountryNumbers?.contains(rndNum) == true) {
            rndNum = Random.nextInt(countriesInfo!!.size)
        }
        previousCountryNumbers?.add(rndNum)
        currentImageNumber = rndNum
        currentImage.postValue(COUNTRY_FLAGS_URL + countriesInfo?.get(currentImageNumber)?.alpha2Code + FLAT_64)

        currentImageName = countriesInfo!![currentImageNumber].name
    }

    fun isCorrect(answer: String) {
        if(answer == currentImageName) {
            isAnswerCorrect.postValue(true)
            currentQuestion++
            currentQuestionFlag.postValue(currentQuestion)
        }
        else {
            isAnswerCorrect.postValue(false)
            currentQuestion++
            currentQuestionFlag.postValue(currentQuestion)
        }
    }

//    private fun checkIfNumberExist(number: Int) {
//        while(previousCountryNumbers?.contains(number) == true) {
//            currentImageNumber = Random.nextInt(countriesInfo!!.size)
//        }
//        previousCountryNumbers?.add(currentImageNumber)
//    }

}