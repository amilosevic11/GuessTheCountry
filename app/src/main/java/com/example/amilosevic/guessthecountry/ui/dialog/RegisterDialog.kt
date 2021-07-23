package com.example.amilosevic.guessthecountry.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.amilosevic.guessthecountry.databinding.RegisterDialogBinding
import com.example.amilosevic.guessthecountry.ui.viewmodels.RegistrationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterDialog : DialogFragment() {

    private lateinit var binding: RegisterDialogBinding
    private val viewModel by viewModel<RegistrationViewModel>()
    private var isRegistered: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = RegisterDialogBinding.inflate(layoutInflater).also {
            val email = it.etEmailRegister
            val password = it.etPasswordRegister

            it.btnRegisterUser.setOnClickListener {
//                viewModel.register(email.text.toString(), password.text.toString())
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.register(email.text.toString(), password.text.toString())
                }
                isRegistered = viewModel.isRegistered()
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