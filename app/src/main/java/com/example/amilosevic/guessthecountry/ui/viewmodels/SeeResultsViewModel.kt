package com.example.amilosevic.guessthecountry.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.amilosevic.guessthecountry.model.ResultDetails

class SeeResultsViewModel(private val resultDetails: ResultDetails) : ViewModel() {

    private var _selectedResult: MutableLiveData<ResultDetails> = MutableLiveData()
    var selectedResult: LiveData<ResultDetails> = _selectedResult

    fun setSelectedResult(selectedResultDetails: ResultDetails) {
        _selectedResult.postValue(selectedResultDetails)
    }
}