package com.example.amilosevic.guessthecountry.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amilosevic.guessthecountry.ui.recyclerview.ResultsRecyclerAdapter
import com.example.amilosevic.guessthecountry.databinding.ActivitySeeResultsBinding
import com.example.amilosevic.guessthecountry.model.ResultDetails
import com.example.amilosevic.guessthecountry.ui.viewmodels.SeeResultsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeeResultsActivity : AppCompatActivity(), ResultsRecyclerAdapter.OnItemClickListener {

    private lateinit var binding: ActivitySeeResultsBinding
    private lateinit var seeResultsRecyclerAdapter: ResultsRecyclerAdapter
    private val seeResultsViewModel by viewModel<SeeResultsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        CoroutineScope(Dispatchers.Default).launch {
//            seeResultsViewModel.addToDatabase()
//        }
        CoroutineScope(Dispatchers.Default).launch {
            seeResultsViewModel.getDataFromDatabase()
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvSeeResults.apply {
            layoutManager = LinearLayoutManager(this@SeeResultsActivity)
            seeResultsRecyclerAdapter = ResultsRecyclerAdapter(this@SeeResultsActivity)
            adapter = seeResultsRecyclerAdapter
        }
    }

    override fun onItemClick(position: Int) {

    }

}