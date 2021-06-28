package com.example.amilosevic.guessthecountry.activities

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.amilosevic.guessthecountry.R
import com.example.amilosevic.guessthecountry.databinding.ActivityPlayQuizBinding
import com.example.amilosevic.guessthecountry.svg.SvgSoftwareLayerSetter
import com.example.amilosevic.guessthecountry.viewmodels.PlayQuizViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.InputStream
import kotlin.random.Random

class PlayQuizActivity : AppCompatActivity() {

    private val playQuizViewModel by viewModel<PlayQuizViewModel>()

    private lateinit var binding: ActivityPlayQuizBinding

    private lateinit var requestBuilder: RequestBuilder<PictureDrawable>

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
//            requestBuilder = Glide.with(this)
//
        })

        playQuizViewModel.currentImage.observe(this, {
            Glide.with(this)
                .load(it)
                .into(binding.ivFlag)
        })

    }
}