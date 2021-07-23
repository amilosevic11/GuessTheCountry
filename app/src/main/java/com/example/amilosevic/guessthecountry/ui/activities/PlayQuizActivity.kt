package com.example.amilosevic.guessthecountry.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.amilosevic.guessthecountry.databinding.ActivityPlayQuizBinding
import com.example.amilosevic.guessthecountry.ui.viewmodels.PlayQuizViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayQuizActivity : AppCompatActivity() {

    private val playQuizViewModel by viewModel<PlayQuizViewModel>()
    private lateinit var binding: ActivityPlayQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayQuizBinding.inflate(layoutInflater).also {
            val binding = it
            it.progressBarHorizontal.progress = 0

            val firstButton = it.btnFirstAnswer
            it.btnFirstAnswer.setOnClickListener {
                playQuizViewModel.isCorrect(firstButton.text.toString())
                binding.progressBarHorizontal.progress++
                playQuizViewModel.setRandomCountries()
            }

            val secondButton = it.btnSecondAnswer
            it.btnSecondAnswer.setOnClickListener {
                playQuizViewModel.isCorrect(secondButton.text.toString())
                binding.progressBarHorizontal.progress++
                playQuizViewModel.setRandomCountries()
            }

            val thirdButton = it.btnThirdAnswer
            it.btnThirdAnswer.setOnClickListener {
                playQuizViewModel.isCorrect(thirdButton.text.toString())
                binding.progressBarHorizontal.progress++
                playQuizViewModel.setRandomCountries()
            }

            val fourthButton = it.btnFourthAnswer
            it.btnFourthAnswer.setOnClickListener {
                playQuizViewModel.isCorrect(fourthButton.text.toString())
                binding.progressBarHorizontal.progress++
                playQuizViewModel.setRandomCountries()
            }
        }
        setContentView(binding.root)

        playQuizViewModel.getAllCountries()

        playQuizViewModel.myResponse.observe(this, {
            binding.btnFirstAnswer.text = it[playQuizViewModel.currentFirstCountry].name
            binding.btnSecondAnswer.text = it[playQuizViewModel.currentSecondCountry].name
            binding.btnThirdAnswer.text = it[playQuizViewModel.currentThirdCountry].name
            binding.btnFourthAnswer.text = it[playQuizViewModel.currentImageNumber].name
        })

        playQuizViewModel.currentImage.observe(this, {
            Glide.with(this)
                .load(it)
                .into(binding.ivFlag)
        })

        playQuizViewModel.currentQuestionFlag.observe(this, {
            binding.btnFirstAnswer.text = playQuizViewModel.countriesInfo!![playQuizViewModel.currentFirstCountry].name
            binding.btnSecondAnswer.text = playQuizViewModel.countriesInfo!![playQuizViewModel.currentSecondCountry].name
            binding.btnThirdAnswer.text = playQuizViewModel.countriesInfo!![playQuizViewModel.currentThirdCountry].name
            binding.btnFourthAnswer.text = playQuizViewModel.countriesInfo!![playQuizViewModel.currentImageNumber].name
        })
    }
}