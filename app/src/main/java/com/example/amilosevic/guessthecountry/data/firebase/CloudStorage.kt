package com.example.amilosevic.guessthecountry.data.firebase

import android.net.Uri
import android.util.Log
import com.example.amilosevic.guessthecountry.utils.Constants.Companion.IMAGE_PATH
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

class CloudStorage(storageReference: FirebaseStorage) {

    val storageRef = storageReference.reference
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var index: Int? = auth.currentUser?.email?.indexOf('@')
    var username: String? = index?.let { auth.currentUser?.email?.substring(0, it) }
    var imagesRef: StorageReference? = storageRef.child(username + ".jpg")
    lateinit var downloadUri: Uri
    lateinit var list: MutableList<StorageReference>

    suspend fun uploadImage(imageUri: Uri) {
        try {
            imagesRef?.putFile(imageUri)?.addOnSuccessListener {
                Log.e("UPLOADING", "DONE")
            }?.await()
        } catch (e: Exception) {
            Log.e("Downloading photo", e.toString())
        }
    }

    suspend fun downloadPhoto(): Uri? {

        try {
            storageRef.listAll().addOnSuccessListener {
                list = it.items
            }.await()
            for(item in list) {
                if(item.path.contains(username.toString())) {
                    Log.v("jbgPath", IMAGE_PATH + username + ".jpg")
                    storageRef.child(username + ".jpg").downloadUrl.addOnSuccessListener {
                        Log.e("DownloadSuccess", "SUCCESS")
                        downloadUri = it
                    }.addOnFailureListener {
                        Log.e("DownloadError", it.toString())
                    }.await()
                }
                return downloadUri
            }
        } catch (e: Exception) {
            Log.e("Downloading photo", e.toString())
        }

        return null
    }
}