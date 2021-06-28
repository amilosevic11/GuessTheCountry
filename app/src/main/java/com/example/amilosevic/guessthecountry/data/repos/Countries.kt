package com.example.amilosevic.guessthecountry.data.repos


import com.google.gson.annotations.SerializedName

data class Countries (
    val countriesInfo: CountriesInfo
        ) : ArrayList<CountriesInfo>()