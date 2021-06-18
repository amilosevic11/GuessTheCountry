package com.example.amilosevic.guessthecountry.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amilosevic.guessthecountry.data.firebase.FirestoreDatabase

class PlayOrSeeResultsViewModel(private val database: FirestoreDatabase) : ViewModel() {

    var isImageTaken = MutableLiveData<Boolean>()
    var takenImage = MutableLiveData<Bitmap>()

    fun sendImage(image: Bitmap) {
        takenImage.postValue(image)
    }
}