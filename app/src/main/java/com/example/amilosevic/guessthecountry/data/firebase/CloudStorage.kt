package com.example.amilosevic.guessthecountry.data.firebase

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

class CloudStorage(private val storageReference: FirebaseStorage) {

    val storageRef = storageReference.reference
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var index: Int? = auth.currentUser?.email?.indexOf('@')
    var username: String? = index?.let { auth.currentUser?.email?.substring(0, it) }
    var imagesRef: StorageReference? = storageRef.child(username + ".jpg")

    suspend fun uploadImage(imageUri: Uri) {
        if(imageUri != null) {
            imagesRef?.putFile(imageUri)?.addOnSuccessListener {
                Log.e("UPLOADING", "DONE")
            }?.await()
        }
        else {
            Log.e("UPLOADINGfailed", "FAIL")
        }
    }
}