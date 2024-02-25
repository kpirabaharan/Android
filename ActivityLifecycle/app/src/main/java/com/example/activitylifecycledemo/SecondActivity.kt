package com.example.activitylifecycledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MY-TAG", "SecondActivity: onCreate")
        setContentView(R.layout.activity_second)
        val userName = intent.getStringExtra("USER")
        val textView = findViewById<TextView>(R.id.tvOffer)
        val message = "$userName ,you will get  free access to all the content for one month "
        textView.text = message
    }

    override fun onStart() {
        super.onStart()
        Log.i("MY-TAG", "SecondActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MY-TAG", "SecondActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MY-TAG", "SecondActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MY-TAG", "SecondActivity: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MY-TAG", "SecondActivity: onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MY-TAG", "SecondActivity: onRestart")
    }
}