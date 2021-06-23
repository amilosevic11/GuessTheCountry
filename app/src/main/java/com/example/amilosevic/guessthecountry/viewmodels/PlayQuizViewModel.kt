package com.example.amilosevic.guessthecountry.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amilosevic.guessthecountry.model.User

class PlayQuizViewModel(private val user: User) : ViewModel() {

    var users = MutableLiveData<List<User>>()


}