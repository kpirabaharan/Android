package com.example.roomdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {
        val subscribers = repository.subscribers

        val inputName = MutableLiveData<String>()
        val inputEmail = MutableLiveData<String>()

        val saveOrUpdateButtonText = MutableLiveData<String>()
        val clearAllOrDeleteButtonText = MutableLiveData<String>()

        init {
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
        }

        fun saveOrUpdate() {
                val name = inputName.value!!
                val email = inputEmail.value!!
                insertSubscriber(Subscriber(0, name, email))
                inputName.value = ""
                inputEmail.value = ""
        }

        fun clearAllOrDelete() {
                deleteAllSubscribers()
        }

        fun insertSubscriber(subscriber: Subscriber) = viewModelScope.launch {
                repository.insert(subscriber)
        }

        fun updateSubscriber(subscriber: Subscriber) = viewModelScope.launch {
                repository.update(subscriber)
        }

        fun deleteSubscriber(subscriber: Subscriber) = viewModelScope.launch {
                repository.delete(subscriber)
        }

        fun deleteAllSubscribers() = viewModelScope.launch {
                repository.deleteAll()
        }
}