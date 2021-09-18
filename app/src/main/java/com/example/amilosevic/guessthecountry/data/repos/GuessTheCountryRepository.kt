package com.example.amilosevic.guessthecountry.data.repos

import com.example.amilosevic.guessthecountry.data.api.RetrofitInstance
import com.example.amilosevic.guessthecountry.model.CountriesInfo

class GuessTheCountryRepository {

    suspend fun getAllCountries() : ArrayList<CountriesInfo> {
        return RetrofitInstance.api.getAllCountries()
    }
}