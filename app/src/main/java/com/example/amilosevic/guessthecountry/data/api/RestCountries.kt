package com.example.amilosevic.guessthecountry.data.api

import com.example.amilosevic.guessthecountry.model.RestCountriesResponse
import retrofit2.http.GET

interface RestCountries {

    @GET("all")
    suspend fun getAllCountries() : ArrayList<RestCountriesResponse>
}