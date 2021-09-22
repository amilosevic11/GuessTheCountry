package com.example.amilosevic.guessthecountry.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.amilosevic.guessthecountry.ui.dialog.RegisterDialog
import com.example.amilosevic.guessthecountry.databinding.ActivityLoginBinding
import com.example.amilosevic.guessthecountry.ui.viewmodels.RegistrationViewModel
import kotlinx.coroutines.*
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
                CoroutineScope(Dispatchers.Default).launch {
                    viewModel.login(etEmail.text.toString(), etPassword.text.toString())
                }
            }

            it.btnRegister.setOnClickListener {
                RegisterDialog().show(supportFragmentManager, "registration")
            }
        }
        setContentView(binding.root)
        viewModel.isSignedIn.observe(this, {
            if (it) {
                val intent = Intent(this, PlayOrSeeResultsActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onStart() {
        super.onStart()

        if(viewModel.isSigned()) {
            val intent = Intent(this, PlayOrSeeResultsActivity::class.java)
            startActivity(intent)
        }
    }
}