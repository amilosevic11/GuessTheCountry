package com.example.amilosevic.guessthecountry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amilosevic.guessthecountry.databinding.ActivityPlayOrSeeResultsBinding
import com.example.amilosevic.guessthecountry.viewmodel.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayOrSeeResults : AppCompatActivity() {
    private val viewModel by viewModel<RegistrationViewModel>()

    private lateinit var binding: ActivityPlayOrSeeResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayOrSeeResultsBinding.inflate(layoutInflater)
        binding.tvUsername.text = "Hello " + viewModel.getCurrentUser()?.email

        setContentView(binding.root)
    }
}