package com.example.amilosevic.guessthecountry.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.amilosevic.guessthecountry.databinding.ActivityPlayOrSeeResultsBinding
import com.example.amilosevic.guessthecountry.dialog.LoadImageDialog
import com.example.amilosevic.guessthecountry.viewmodels.PlayOrSeeResultsViewModel
import com.example.amilosevic.guessthecountry.viewmodels.RegistrationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.IOException

class PlayOrSeeResultsActivity : AppCompatActivity() {
    private val viewModel by viewModel<RegistrationViewModel>()
    private val playOrSeeResultsViewModel by viewModel<PlayOrSeeResultsViewModel>()

    private lateinit var binding: ActivityPlayOrSeeResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayOrSeeResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvUsername.text = "Hello " + (viewModel.getCurrentUser()?.email ?: "null")
        //viewModel.currentUser.observe(this, Observer { binding.tvUsername.text = "Hello " + it.uid })

        binding.btnSignOut.setOnClickListener {
            viewModel.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnPlay.setOnClickListener {
            val intent = Intent(this, PlayQuizActivity::class.java)
            startActivity(intent)
        }

        binding.ivUserPhoto.setOnClickListener {
            LoadImageDialog().show(supportFragmentManager, "Load image dialog")
        }

        playOrSeeResultsViewModel.takenImage.observe(this, Observer {
            if (it != null ) {
                binding.ivUserPhoto.setImageBitmap(it)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == LoadImageDialog.CAMERA_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage = data?.extras?.get("data") as Bitmap
            playOrSeeResultsViewModel.sendImage(takenImage)
        }
        else if(requestCode == LoadImageDialog.GALLERY_CODE && resultCode == Activity.RESULT_OK) {
//            val selectedImage = data?.extras?.get("data") as Bitmap
//            playOrSeeResultsViewModel.sendImage(selectedImage)
            if(data == null || data.data == null) return

            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data.data)
                playOrSeeResultsViewModel.sendImage(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
//    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
//        playOrSeeResultsViewModel.takenImage.observe(this, Observer {
//            Log.v("jebiga", it.toString())
//            binding.ivUserPhoto.setImageBitmap(it)
//        })
//        return super.onCreateView(name, context, attrs)
//    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if(requestCode == 202 && resultCode == Activity.RESULT_OK) {
//            data?.data?.let {
//
//            }
//        }
//        else {
//            super.onActivityResult(requestCode, resultCode, data)
//        }
//    }

}