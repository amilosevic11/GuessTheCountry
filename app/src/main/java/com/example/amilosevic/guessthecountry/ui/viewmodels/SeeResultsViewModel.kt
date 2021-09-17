package com.example.amilosevic.guessthecountry.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amilosevic.guessthecountry.data.firebase.CloudStorage
import com.example.amilosevic.guessthecountry.model.ResultDetails
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SeeResultsViewModel(private val firestore: FirebaseFirestore, private val storage: CloudStorage) : ViewModel() {

   suspend fun addToDatabase() {

      val results = hashMapOf(
         "username" to "Krčačan",
         "score" to "2/5",
         "data" to "22.5.2021."
      )

      firestore.collection("results")
         .add(results)
         .addOnSuccessListener {
            Log.d("SUKSES", "svevalja")
         }
         .addOnFailureListener {
            Log.e("NekajNevala", "zbeksalose")
         }.await()
   }

   suspend fun getDataFromDatabase() {
      firestore.collection("results")
         .get()
         .addOnSuccessListener {
            Log.d("DohvatiliSmmo", it.toString())
         }
         .addOnCompleteListener {
            if(it.isSuccessful) {
               for(document in it.result!!) {
                  Log.d("NEKAJDE", document.data.toString())
               }
            }
         }
         .addOnFailureListener {
            Log.e("nekajSeOpetZbeksalo", "nevala")
         }.await()
   }
}