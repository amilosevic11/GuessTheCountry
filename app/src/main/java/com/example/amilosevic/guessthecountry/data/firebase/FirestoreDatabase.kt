package com.example.amilosevic.guessthecountry.data.firebase

import android.graphics.Bitmap
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference

class FirestoreDatabase(private val database: FirebaseFirestore) {

    //private val myRef = database.reference

    private lateinit var storageReference: StorageReference
}