package com.example.coding_challenge_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityModelFactory(private val startingTotal: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityModel::class.java)) {
            return MainActivityModel(startingTotal) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}