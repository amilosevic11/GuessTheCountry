package com.example.amilosevic.guessthecountry.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.amilosevic.guessthecountry.dialog.RegisterDialog
import com.example.amilosevic.guessthecountry.databinding.ActivityLoginBinding
import com.example.amilosevic.guessthecountry.viewmodels.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModel<RegistrationViewModel>()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater).also {
            val etEmail = it.etEmail
            val etPassword = it.etPassword

            viewModel.isSignedIn.observe(this, Observer {

                if(it != false) {
                    val intent = Intent(this, PlayOrSeeResultsActivity::class.java)
                    startActivity(intent)
                }
            })

            it.btnLogin.setOnClickListener {
//                viewModel.login(etEmail.toString(), etPassword.toString())

            }

            it.btnRegister.setOnClickListener {
                RegisterDialog().show(supportFragmentManager, "registration")
            }
        }
        setContentView(binding.root)
    }
}