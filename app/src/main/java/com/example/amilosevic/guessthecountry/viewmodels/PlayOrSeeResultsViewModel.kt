package com.example.amilosevic.guessthecountry.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amilosevic.guessthecountry.data.firebase.CloudStorage

class PlayOrSeeResultsViewModel(private val database: CloudStorage) : ViewModel() {

    var isImageTaken = MutableLiveData<Boolean>()
    var takenImage = MutableLiveData<Bitmap>()

    fun sendImage(image: Bitmap) {
        takenImage.postValue(image)
    }
}