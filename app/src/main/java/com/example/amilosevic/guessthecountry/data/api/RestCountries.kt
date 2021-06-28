package com.example.amilosevic.guessthecountry.data.api

import com.example.amilosevic.guessthecountry.data.repos.CountriesInfo
import retrofit2.http.GET

interface RestCountries {

    @GET("all")
    suspend fun getAllCountries() : ArrayList<CountriesInfo>
}