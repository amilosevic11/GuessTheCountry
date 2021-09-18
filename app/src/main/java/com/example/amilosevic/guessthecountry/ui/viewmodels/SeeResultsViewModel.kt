package com.example.amilosevic.guessthecountry.ui.viewmodels

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amilosevic.guessthecountry.data.firebase.CloudStorage
import com.example.amilosevic.guessthecountry.model.ResultDetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SeeResultsViewModel(private val firestore: FirebaseFirestore, private val storage: CloudStorage, private val auth: FirebaseAuth) : ViewModel() {

   var index: Int? = auth.currentUser?.email?.indexOf('@')
   var username: String? = index?.let { auth.currentUser?.email?.substring(0, it) }

   var didUploadResult: MutableLiveData<Boolean> = MutableLiveData()
   var didFetchData: MutableLiveData<Boolean> = MutableLiveData()

   private var resultDetails: ArrayList<ResultDetails> = ArrayList()

   @RequiresApi(Build.VERSION_CODES.O)
   suspend fun addToDatabase(score: String) {

      val results = hashMapOf(
         "username" to username,
         "score" to score,
         "date" to LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm")),
         "imageUrl" to storage.downloadPhoto().toString()
      )

      firestore.collection("results")
         .add(results)
         .addOnSuccessListener {
            Log.d("SUKSES", "svevalja")
            didUploadResult.postValue(true)
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
                  val date = document.data["date"].toString()
                  val imageUrl = document.data["imageUrl"].toString()
                  val score = document.data["score"].toString()
                  val username = document.data["username"].toString()

                  resultDetails.add(ResultDetails(date, imageUrl, score, username))

                  Log.d("NEKAJDE", document.data["username"].toString())

                  Log.d("responsbrat", resultDetails[0].toString())
               }
            }
            didFetchData.postValue(true)
         }
         .addOnFailureListener {
            Log.e("nekajSeOpetZbeksalo", "nevala")
         }.await()
   }

   fun getResultDetails(): ArrayList<ResultDetails> {
      return resultDetails
   }
}