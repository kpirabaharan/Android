package com.example.coroutinesdemo

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserDataManagerTwo {
    private var count = 0
    private lateinit var deferred: Deferred<Int>

    suspend fun getTotalUserCount(): Int {
        coroutineScope {
            launch(Dispatchers.IO) {
                delay(3000)
                count = 50
            }

            deferred = async(Dispatchers.IO) {
                delay(5000)
                return@async 70
            }
        }
        return count + deferred.await()
    }
}