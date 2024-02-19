package com.example.coding_challenge_viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coding_challenge_viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityModel
    private lateinit var viewModelFactory: MainActivityModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModelFactory = MainActivityModelFactory(125)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityModel::class.java]

        viewModel.sumData.observe(this, Observer{
            binding.resultTextView.text = it.toString()
        })

        binding.addButton.setOnClickListener {
            viewModel.addToSum(binding.inputEditText.text.toString().toInt())
        }
    }
}