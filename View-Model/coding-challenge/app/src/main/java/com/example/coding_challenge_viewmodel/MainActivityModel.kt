package com.example.coding_challenge_viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityModel(startingTotal: Int) : ViewModel() {
    private var sum = MutableLiveData<Int>()
    val sumData: LiveData<Int> get() = sum

    init {
        sum.value = startingTotal
    }

    fun addToSum(value: Int) {
        sum.value = sum.value?.plus(value)
    }
}