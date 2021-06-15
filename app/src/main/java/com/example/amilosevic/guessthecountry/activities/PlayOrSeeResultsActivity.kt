package com.example.amilosevic.guessthecountry.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.Observer
import com.example.amilosevic.guessthecountry.databinding.ActivityPlayOrSeeResultsBinding
import com.example.amilosevic.guessthecountry.viewmodels.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayOrSeeResultsActivity : AppCompatActivity() {
    private val viewModel by viewModel<RegistrationViewModel>()

    private lateinit var binding: ActivityPlayOrSeeResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayOrSeeResultsBinding.inflate(layoutInflater)
        //binding.tvUsername.text = "Hello " + viewModel.getCurrentUser().email

        viewModel.currentUser.observe(this, Observer { binding.tvUsername.text = "Hello " + it.uid })

        binding.btnSignOut.setOnClickListener {
            viewModel.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnPlay.setOnClickListener {
            val intent = Intent(this, PlayQuizActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }

}