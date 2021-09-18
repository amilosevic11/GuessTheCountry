package com.example.amilosevic.guessthecountry.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.amilosevic.guessthecountry.databinding.ActivityPlayQuizBinding
import com.example.amilosevic.guessthecountry.ui.viewmodels.PlayQuizViewModel
import com.example.amilosevic.guessthecountry.ui.viewmodels.SeeResultsViewModel
import com.example.amilosevic.guessthecountry.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayQuizActivity : AppCompatActivity() {

    private val playQuizViewModel by viewModel<PlayQuizViewModel>()
    private val seeResultsViewModel by viewModel<SeeResultsViewModel>()
    private lateinit var binding: ActivityPlayQuizBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayQuizBinding.inflate(layoutInflater).also {
            val binding = it
            it.progressBarHorizontal.progress = 0

            val firstButton = it.btnFirstAnswer
            it.btnFirstAnswer.setOnClickListener {
                playQuizViewModel.isCorrect(firstButton.text.toString())
                binding.progressBarHorizontal.progress++
            }

            val secondButton = it.btnSecondAnswer
            it.btnSecondAnswer.setOnClickListener {
                playQuizViewModel.isCorrect(secondButton.text.toString())
                binding.progressBarHorizontal.progress++
            }

            val thirdButton = it.btnThirdAnswer
            it.btnThirdAnswer.setOnClickListener {
                playQuizViewModel.isCorrect(thirdButton.text.toString())
                binding.progressBarHorizontal.progress++
            }

            val fourthButton = it.btnFourthAnswer
            it.btnFourthAnswer.setOnClickListener {
                playQuizViewModel.isCorrect(fourthButton.text.toString())
                binding.progressBarHorizontal.progress++
            }
        }

        playQuizViewModel.getAllCountries()

        playQuizViewModel.didFetchCountries.observe(this, {
            binding.btnFirstAnswer.text = playQuizViewModel.getRandomCountryName()
            binding.btnSecondAnswer.text = playQuizViewModel.getRandomCountryName()
            binding.btnThirdAnswer.text = playQuizViewModel.getRandomCountryName()
            binding.btnFourthAnswer.text = playQuizViewModel.getRandomCountryName()

            Glide.with(this)
                .load(Constants.COUNTRY_FLAGS_URL + playQuizViewModel.getRandomCountryImage() + Constants.FLAT_64)
                .into(binding.ivFlag)
        })

        playQuizViewModel.currentQuestionFlag.observe(this, {
            binding.btnFirstAnswer.text = playQuizViewModel.getRandomCountryName()
            binding.btnSecondAnswer.text = playQuizViewModel.getRandomCountryName()
            binding.btnThirdAnswer.text = playQuizViewModel.getRandomCountryName()
            binding.btnFourthAnswer.text = playQuizViewModel.getRandomCountryName()

            Glide.with(this)
                .load(Constants.COUNTRY_FLAGS_URL + playQuizViewModel.getRandomCountryImage() + Constants.FLAT_64)
                .into(binding.ivFlag)
        })

        playQuizViewModel.allQuestionsAnswered.observe(this, {
            CoroutineScope(Dispatchers.Default).launch {
                seeResultsViewModel.addToDatabase(playQuizViewModel.getCorrectAnswers().toString())
            }
        })

        seeResultsViewModel.didUploadResult.observe(this, {
            finish()
        })

        playQuizViewModel.isAnswerCorrect.observe(this, {
            if(it){
                //TODO neki zvuk kad je tocan odgovor
            }
            else {
                //TODO neki zvuk kad odgovor nije tocan
            }
        })

        setContentView(binding.root)
    }
}