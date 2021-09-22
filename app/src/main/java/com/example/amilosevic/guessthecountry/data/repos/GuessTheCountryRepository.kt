package com.example.amilosevic.guessthecountry.data.repos

import android.util.Log
import com.example.amilosevic.guessthecountry.data.api.RetrofitInstance
import com.example.amilosevic.guessthecountry.model.RestCountriesResponse

class GuessTheCountryRepository {

    suspend fun getAllCountries() : ArrayList<RestCountriesResponse> {
        try {
            return RetrofitInstance.api.getAllCountries()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        return arrayListOf()
    }
}