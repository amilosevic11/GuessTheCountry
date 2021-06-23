package com.example.amilosevic.guessthecountry.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amilosevic.guessthecountry.data.firebase.CloudStorage
import com.example.amilosevic.guessthecountry.data.firebase.FirestoreDatabase
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class PlayOrSeeResultsViewModel(private val storage: CloudStorage) : ViewModel() {

    var isImageTaken = MutableLiveData<Boolean>()
    var takenImage = MutableLiveData<Bitmap>()

    fun sendImage(image: Bitmap) {
        takenImage.postValue(image)
    }

    suspend fun uploadImage(imageUri: Uri) {
        storage.uploadImage(imageUri)
    }

    fun getImageUri(context: Context, image: Bitmap): Uri {
        var baos: ByteArrayOutputStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        return Uri.parse(MediaStore.Images.Media.insertImage(context.contentResolver, image, "Title", null))
    }
}