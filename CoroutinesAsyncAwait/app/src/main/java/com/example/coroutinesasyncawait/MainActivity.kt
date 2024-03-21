package com.example.coroutinesasyncawait

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Running as main thread allows us to update the UI
        CoroutineScope(Dispatchers.Main).launch {
            Log.i("MyTag", "App started")
            val stock1 = async(Dispatchers.IO) { getStock1() }
            val stock2 = async(Dispatchers.IO) { getStock2() }
            val totalStock = stock1.await() + stock2.await()
            Toast.makeText(applicationContext, "Total stock price is $totalStock", Toast.LENGTH_LONG).show()
            Log.i("MyTag", "Total stock price is $totalStock")
        }

        // Best practice is to run using IO thread then switch to main thread to update UI
//        CoroutineScope(Dispatchers.IO).launch {
//            Log.i("MyTag", "App started")
//            val stock1 = async { getStock1() }
//            val stock2 = async { getStock2() }
//            val totalStock = stock1.await() + stock2.await()
//            Log.i("MyTag", "Total stock price is $totalStock")
//        }
    }
}

private suspend fun getStock1(): Int {
    delay(10000)
    Log.i("MyTag", "Stock 1 returned")
    return 55000
}

private suspend fun getStock2(): Int {
    delay(8000)
    Log.i("MyTag", "Stock 2 returned")
    return 35000
}