package com.example.amilosevic.guessthecountry.data.api

import com.squareup.okhttp.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

const val BASE_URL = "https://restcountries.eu/rest/v2/"

interface RestCountries {

    @GET("all")
    fun getAllCountries():Response<ResponseBody>
}