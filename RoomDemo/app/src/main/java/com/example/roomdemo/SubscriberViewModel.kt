package com.example.roomdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {
        val subscribers = repository.subscribers
        private var isUpdateOrDelete = false
        private lateinit var subscriberToUpdateOrDelete: Subscriber

        val inputName = MutableLiveData<String>()
        val inputEmail = MutableLiveData<String>()

        val saveOrUpdateButtonText = MutableLiveData<String>()
        val clearAllOrDeleteButtonText = MutableLiveData<String>()
        val resetButtonText = MutableLiveData<String>()

        private val statusMessage = MutableLiveData<Event<String>>()

        val message: LiveData<Event<String>>
                get() = statusMessage

        init {
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                resetButtonText.value = "Reset"
        }

        fun saveOrUpdate() {
                if (isUpdateOrDelete) {
                        subscriberToUpdateOrDelete.name = inputName.value!!
                        subscriberToUpdateOrDelete.email = inputEmail.value!!
                        updateSubscriber(subscriberToUpdateOrDelete)
                } else {
                        val name = inputName.value!!
                        val email = inputEmail.value!!
                        insertSubscriber(Subscriber(0, name, email))
                        inputName.value = ""
                        inputEmail.value = ""
                }
        }

        fun clearAllOrDelete() {
                if (isUpdateOrDelete) deleteSubscriber(subscriberToUpdateOrDelete)
                else deleteAllSubscribers()
        }

        fun reset() {
                inputName.value = ""
                inputEmail.value = ""
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
        }

        fun insertSubscriber(subscriber: Subscriber) = viewModelScope.launch {
                repository.insert(subscriber)
                withContext(
                        Dispatchers.Main
                ) {
                        statusMessage.value = Event("Subscriber Inserted Successfully")
                }
        }

        fun updateSubscriber(subscriber: Subscriber) = viewModelScope.launch {
                repository.update(subscriber)
                withContext(
                        Dispatchers.Main
                ) {
                        inputName.value = ""
                        inputEmail.value = ""
                        isUpdateOrDelete = false
                        saveOrUpdateButtonText.value = "Save"
                        clearAllOrDeleteButtonText.value = "Clear All"
                        statusMessage.value = Event("Subscriber Updated Successfully")
                }
        }

        fun deleteSubscriber(subscriber: Subscriber) = viewModelScope.launch {
                repository.delete(subscriber)
                withContext(
                        Dispatchers.Main
                ) {
                        inputName.value = ""
                        inputEmail.value = ""
                        isUpdateOrDelete = false
                        saveOrUpdateButtonText.value = "Save"
                        clearAllOrDeleteButtonText.value = "Clear All"
                        statusMessage.value = Event("Subscriber Deleted Successfully")
                }
        }

        fun deleteAllSubscribers() = viewModelScope.launch {
                repository.deleteAll()
                withContext(
                        Dispatchers.Main
                ) {
                        statusMessage.value = Event("All Subscribers Deleted Successfully")
                }
        }

        fun initUpdateAndDelete(subscriber: Subscriber) {
                inputName.value = subscriber.name
                inputEmail.value = subscriber.email
                isUpdateOrDelete = true
                subscriberToUpdateOrDelete = subscriber
                saveOrUpdateButtonText.value = "Update"
                clearAllOrDeleteButtonText.value = "Delete"
        }
}