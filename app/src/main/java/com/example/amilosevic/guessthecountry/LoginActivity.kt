package com.example.amilosevic.guessthecountry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amilosevic.guessthecountry.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater).also {
            val etUsername = it.etUsername
            val etPassword = it.etPassword
            it.btnLogin.setOnClickListener {
                val intent = Intent(this, PlayOrSeeResults::class.java)
                intent.putExtra("username", etUsername.text.toString())
                intent.putExtra("password", etPassword.text.toString())
                startActivity(intent)
            }
            it.btnRegister.setOnClickListener {
                RegisterDialog().show(supportFragmentManager, "Register Dialog")
            }
        }
        setContentView(binding.root)
    }
}