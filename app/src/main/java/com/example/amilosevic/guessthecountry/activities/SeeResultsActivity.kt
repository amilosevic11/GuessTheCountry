package com.example.amilosevic.guessthecountry.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amilosevic.guessthecountry.ResultsRecyclerAdapter
import com.example.amilosevic.guessthecountry.databinding.ActivitySeeResultsBinding

class SeeResultsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeeResultsBinding
    private lateinit var seeResultsRecyclerAdapter: ResultsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeeResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    fun initRecyclerView() {
        binding.rvSeeResults.apply {
            layoutManager = LinearLayoutManager(this@SeeResultsActivity)
            seeResultsRecyclerAdapter = ResultsRecyclerAdapter()
            adapter = seeResultsRecyclerAdapter
        }
    }

}