package com.example.roomdemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberDatabase
import com.example.roomdemo.db.SubscriberRepository

class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding
        private lateinit var subscriberViewModel: SubscriberViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()
                setContentView(R.layout.activity_main)
                binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
                val dao = SubscriberDatabase.getInstance(application).subscriberDAO
                val repository = SubscriberRepository(dao)
                val factory = SubscriberViewModelFactory(repository)
                subscriberViewModel =
                        ViewModelProvider(this, factory)[SubscriberViewModel::class.java]
                binding.myViewModel = subscriberViewModel
                binding.lifecycleOwner = this
                initRecyclerView()
        }

        private fun initRecyclerView() {
                binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
                displaySubscribersList()
        }

        private fun displaySubscribersList() {
                subscriberViewModel.subscribers.observe(this) {
                        Log.i("MYTAG", it.toString())
                        binding.subscriberRecyclerView.adapter = MyRecyclerViewAdapter(
                                it,
                                { selectedItem: Subscriber -> listItemClicked(selectedItem) })
                }
        }

        private fun listItemClicked(subscriber: Subscriber) {
                Toast.makeText(this, "Selected name is ${subscriber.name}", Toast.LENGTH_LONG)
                        .show()
                subscriberViewModel.initUpdateAndDelete(subscriber)
        }
}