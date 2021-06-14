package com.example.amilosevic.guessthecountry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.amilosevic.guessthecountry.databinding.ActivityLoginBinding
import com.example.amilosevic.guessthecountry.viewmodel.RegistrationViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModel<RegistrationViewModel>()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        binding = ActivityLoginBinding.inflate(layoutInflater).also {
            val etEmail = it.etEmail.toString()
            val etPassword = it.etPassword.toString()

            it.btnLogin.setOnClickListener {
                viewModel.login(etEmail, etPassword)
                val intent = Intent(this, PlayOrSeeResults::class.java)
                startActivity(intent)
            }

            it.btnRegister.setOnClickListener {
                RegisterDialog().show(supportFragmentManager, "registration")
            }
        }
        setContentView(binding.root)
    }
}