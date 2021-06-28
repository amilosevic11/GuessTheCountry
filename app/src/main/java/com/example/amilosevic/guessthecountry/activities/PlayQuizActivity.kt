package com.example.amilosevic.guessthecountry.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.amilosevic.guessthecountry.R
import com.example.amilosevic.guessthecountry.databinding.ActivityPlayQuizBinding
import com.example.amilosevic.guessthecountry.viewmodels.PlayQuizViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayQuizActivity : AppCompatActivity() {

    private val playQuizViewModel by viewModel<PlayQuizViewModel>()

    private lateinit var binding: ActivityPlayQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playQuizViewModel.getAllCountries()
        playQuizViewModel.myResponse.observe(this, {

        })
    }
}