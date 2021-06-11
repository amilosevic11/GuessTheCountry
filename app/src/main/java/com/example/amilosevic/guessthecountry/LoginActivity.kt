package com.example.amilosevic.guessthecountry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amilosevic.guessthecountry.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater).also {
            it.btnLogin.setOnClickListener {

            }
            it.btnRegister.setOnClickListener {
                RegisterDialog().show(supportFragmentManager, "Register Dialog")
            }
        }
        setContentView(binding.root)
    }
}