package com.example.amilosevic.guessthecountry

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.amilosevic.guessthecountry.databinding.RegisterDialogBinding

class RegisterDialog : DialogFragment() {

    private lateinit var binding: RegisterDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RegisterDialogBinding.inflate(layoutInflater).also {
            it.btnRegisterUser.setOnClickListener {

            }
        }
        return inflater.inflate(R.layout.register_dialog, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = RegisterDialogBinding.inflate(layoutInflater)


    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels)
        val height = (resources.displayMetrics.heightPixels * 0.5).toInt()

        dialog!!.window?.setLayout(width, height)
    }

}