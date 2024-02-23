package com.example.twowaybinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel(){
    val username = MutableLiveData<String>()

    init {
        username.value = "John Doe"
    }
}