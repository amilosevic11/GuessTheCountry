package com.example.amilosevic.guessthecountry.data.firebase

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

const val IMAGE_PATH = "guessthecountry-a3e8c.appspot.com/"

class CloudStorage(storageReference: FirebaseStorage) {

    val storageRef = storageReference.reference
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var index: Int? = auth.currentUser?.email?.indexOf('@')
    var username: String? = index?.let { auth.currentUser?.email?.substring(0, it) }
    var imagesRef: StorageReference? = storageRef.child(username + ".jpg")
    lateinit var downloadUri: Uri

    suspend fun uploadImage(imageUri: Uri) {
        imagesRef?.putFile(imageUri)?.addOnSuccessListener {
            Log.e("UPLOADING", "DONE")
        }?.await()
    }

    suspend fun downloadPhoto(): Uri {

//        val ONE_MB: Long = 1024 * 1024
//        imagesRef?.getBytes(ONE_MB)?.addOnSuccessListener {
//            Log.e("Image download", "Success")
//        }?.await()
        Log.v("jbgPath", IMAGE_PATH + username + ".jpg")
        storageRef.child(username + ".jpg").downloadUrl.addOnSuccessListener {
            Log.e("DownloadSuccess", "SUCCESS")
            downloadUri = it
        }.addOnFailureListener {
            Log.e("DownloadError", it.toString())
        }.await()


        return downloadUri
    }
}