package com.example.amilosevic.guessthecountry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amilosevic.guessthecountry.databinding.ActivityPlayOrSeeResultsBinding

class PlayOrSeeResults : AppCompatActivity() {
    private var userName : String ?= ""

    private lateinit var binding: ActivityPlayOrSeeResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayOrSeeResultsBinding.inflate(layoutInflater)
        userName = intent.getStringExtra("username")

        binding.tvUsername.text = "Hello " + userName

        setContentView(binding.root)
    }
}