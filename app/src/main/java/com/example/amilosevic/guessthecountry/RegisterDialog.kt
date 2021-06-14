package com.example.amilosevic.guessthecountry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.amilosevic.guessthecountry.databinding.RegisterDialogBinding
import com.example.amilosevic.guessthecountry.viewmodel.RegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterDialog : DialogFragment() {

    private lateinit var binding: RegisterDialogBinding
    private val viewModel by viewModel<RegistrationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = RegisterDialogBinding.inflate(layoutInflater).also {
            val email = it.etEmailRegister.text.toString()
            val password = it.etPasswordRegister.text.toString()

            it.btnRegisterUser.setOnClickListener {
                viewModel.register(email, password)
            }

        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels)
        val height = (resources.displayMetrics.heightPixels * 0.5).toInt()

        dialog!!.window?.setLayout(width, height)
    }

}