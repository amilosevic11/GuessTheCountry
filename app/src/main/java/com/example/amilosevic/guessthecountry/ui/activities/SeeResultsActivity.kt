package com.example.amilosevic.guessthecountry.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amilosevic.guessthecountry.databinding.ActivitySeeResultsBinding
import com.example.amilosevic.guessthecountry.ui.recyclerview.ResultsRecyclerAdapter
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

        CoroutineScope(Dispatchers.Default).launch {
            seeResultsViewModel.getDataFromDatabase()
        }

        initRecyclerView()

        seeResultsViewModel.didFetchData.observe(this, {
            Log.d("fechamoDatu", "dada")
            seeResultsRecyclerAdapter.addResults(seeResultsViewModel.getResultDetails())
        })
    }

    private fun initRecyclerView() {
        binding.rvSeeResults.apply {
            layoutManager = LinearLayoutManager(this@SeeResultsActivity)
            seeResultsRecyclerAdapter = ResultsRecyclerAdapter(this@SeeResultsActivity)
            adapter = seeResultsRecyclerAdapter

            this.addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onItemClick(position: Int) {
        val item = seeResultsRecyclerAdapter.getItemAt(position)
    }

}