package com.example.amilosevic.guessthecountry.data.firebase

import com.google.firebase.database.FirebaseDatabase

class FirebaseRealtimeDatabase(private val database: FirebaseDatabase) {

    private val myRef = database.reference
}