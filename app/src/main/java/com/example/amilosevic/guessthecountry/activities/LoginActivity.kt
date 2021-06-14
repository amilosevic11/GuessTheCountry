package com.example.amilosevic.guessthecountry.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amilosevic.guessthecountry.dialog.RegisterDialog
import com.example.amilosevic.guessthecountry.databinding.ActivityLoginBinding
import com.example.amilosevic.guessthecountry.viewmodel.RegistrationViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModel<RegistrationViewModel>()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater).also {
            val etEmail = it.etEmail
            val etPassword = it.etPassword

            it.btnLogin.setOnClickListener {
                viewModel.login(etEmail.toString(), etPassword.toString())
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