package com.example.recyclerviewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val fruitsList = listOf<Fruit>(
        Fruit("Mango", "Joe"),
        Fruit("Apple", "Frank"),
        Fruit("Banana", "Tom"),
        Fruit("Orange", "Joe"),
        Fruit("Pineapple", "Alex"),
        Fruit("Grapes", "Joe"),
        Fruit("Watermelon", "Alex"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyvlerView = findViewById<RecyclerView>(R.id.myRecyclerView)
        recyvlerView.setBackgroundColor(Color.YELLOW)
        recyvlerView.layoutManager = LinearLayoutManager(this)
        recyvlerView.adapter = MyRecyclerViewAdapter(fruitsList) { selectedItem: Fruit ->
            listItemClicked(
                selectedItem
            )
        }
    }

    private fun listItemClicked(fruit:Fruit) {
        Toast.makeText(this@MainActivity, "Supplier is: ${fruit.supplier}", Toast.LENGTH_SHORT).show()
    }
}