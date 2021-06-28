package com.example.amilosevic.guessthecountry.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.amilosevic.guessthecountry.R
import com.example.amilosevic.guessthecountry.databinding.ActivityPlayQuizBinding
import com.example.amilosevic.guessthecountry.viewmodels.PlayQuizViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class PlayQuizActivity : AppCompatActivity() {

    private val playQuizViewModel by viewModel<PlayQuizViewModel>()

    private lateinit var binding: ActivityPlayQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playQuizViewModel.getAllCountries()
        playQuizViewModel.myResponse.observe(this, {
            binding.btnFirstAnswer.text = it[Random.nextInt(it.size)].name
            binding.btnSecondAnswer.text = it[Random.nextInt(it.size)].name
            binding.btnThirdAnswer.text = it[Random.nextInt(it.size)].name
            binding.btnFourthAnswer.text = it[Random.nextInt(it.size)].name

//            binding.ivFlag.setImageURI(Uri.parse(it[Random.nextInt(it.size)].flag))
            Glide.with(this)
                .load(it[Random.nextInt(it.size)].flag)
                .into(binding.ivFlag)
        })
    }
}