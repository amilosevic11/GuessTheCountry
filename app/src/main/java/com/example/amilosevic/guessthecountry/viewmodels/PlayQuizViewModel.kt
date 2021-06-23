package com.example.amilosevic.guessthecountry.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amilosevic.guessthecountry.data.repos.UserRepository

class PlayQuizViewModel(private val user: UserRepository) : ViewModel() {

    var users = MutableLiveData<List<UserRepository>>()


}