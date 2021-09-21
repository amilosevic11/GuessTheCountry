package com.example.amilosevic.guessthecountry.data.firebase

import android.net.Uri
import android.util.Log
import com.example.amilosevic.guessthecountry.utils.Constants.Companion.IMAGE_PATH
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

class CloudStorage(storageReference: FirebaseStorage, auth: FirebaseAuth) {

    private val storageRef = storageReference.reference

    private var index: Int? = auth.currentUser?.email?.indexOf('@')
    var username: String? = index?.let { auth.currentUser?.email?.substring(0, it) }
    var imagesRef: StorageReference? = storageRef.child("$username.jpg")

    var usernameImage = username + ".jpg"

    private var downloadUri: Uri = Uri.EMPTY
    private var list: MutableList<StorageReference> = ArrayList()

    suspend fun uploadImage(imageUri: Uri) {
        try {
            imagesRef?.putFile(imageUri)?.addOnSuccessListener {
                Log.e("UPLOADING", imageUri.toString())
            }?.await()
        } catch (e: Exception) {
            Log.e("Upload err photo", e.toString())
        }
    }

    suspend fun downloadPhoto(): Uri {

        try {
            Log.d("tryingToDownload", "willSee")
            storageRef.listAll().addOnSuccessListener {
                Log.d("SuccessDownload?", "isIt")
                list = it.items
            }.await()
            for(item in list) {
                if(item.path.contains(usernameImage)) {
                    Log.v("jbgPath", "$IMAGE_PATH$username.jpg")
                    storageRef.child("$username.jpg").downloadUrl.addOnSuccessListener {
                        Log.e("DownloadSuccess", "SUCCESS")
                        downloadUri = it
                    }.addOnFailureListener {
                        Log.e("DownloadError", it.toString())
                    }.await()
                }
            }
        } catch (e: Exception) {
            Log.e("Downloading photo", e.toString())
        }

        return downloadUri
    }

    fun getStorageRef() : MutableList<StorageReference> {
        return list
    }
}