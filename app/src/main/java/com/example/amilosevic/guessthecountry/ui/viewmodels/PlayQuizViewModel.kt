package com.example.amilosevic.guessthecountry.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amilosevic.guessthecountry.data.repos.CountriesInfo
import com.example.amilosevic.guessthecountry.data.repos.GuessTheCountryRepository
import kotlinx.coroutines.launch
import kotlin.random.Random

class PlayQuizViewModel(private val repository: GuessTheCountryRepository) : ViewModel() {

    var didFetchCountries : MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var countriesInfo : ArrayList<CountriesInfo>
    private var correctAnswers: Int = 0
    var allQuestionsAnswered : MutableLiveData<Boolean> = MutableLiveData()
    var currentQuestionFlag : MutableLiveData<Int> = MutableLiveData()
    var currentQuestion: Int = 0

    var currentImageName: String = ""
    var selectedCountries: ArrayList<CountriesInfo> = ArrayList()
    var selectedCountryImage: String = ""

    fun getAllCountries() {
        viewModelScope.launch {
            countriesInfo = repository.getAllCountries()
            didFetchCountries.postValue(true)
        }
    }

    fun getRandomCountryName() : String {
        if(selectedCountries.size == 3)
            selectedCountries.clear()

        val randomNumber = Random.nextInt(countriesInfo.size)
        val randomCountry = countriesInfo[randomNumber]

        countriesInfo.removeAt(randomNumber)

        selectedCountries.add(randomCountry)

        return randomCountry.name
    }

    fun getRandomCountryImage(): String {
        val rnd = Random.nextInt(selectedCountries.size)
        selectedCountryImage = selectedCountries[rnd].name
        currentImageName = selectedCountries[rnd].alpha2Code

        return currentImageName
    }

    fun isCorrect(answer: String) {

        if(currentQuestion == 4) {
            allQuestionsAnswered.postValue(true);
        }
        else if(answer == selectedCountryImage) {
            correctAnswers++

            currentQuestion++
            currentQuestionFlag.postValue(currentQuestion)
        }
        else {
            currentQuestion++
            currentQuestionFlag.postValue(currentQuestion)
        }

        Log.d("currentImageName", selectedCountryImage)
        Log.d("clickedAnswer", answer)
        Log.d("isAnswerCorrect", correctAnswers.toString())
    }

}