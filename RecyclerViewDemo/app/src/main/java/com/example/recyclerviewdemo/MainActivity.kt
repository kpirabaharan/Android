package com.example.recyclerviewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyvlerView = findViewById<RecyclerView>(R.id.myRecyclerView)
        recyvlerView.setBackgroundColor(Color.YELLOW)
        recyvlerView.layoutManager = LinearLayoutManager(this)
    }
}