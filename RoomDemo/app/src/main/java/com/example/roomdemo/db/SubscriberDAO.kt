//File is for database interactions
package com.example.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SubscriberDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscriber(subscriber: Subscriber): Long

    @Update
    suspend fun  updateSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAllSubscribers(): Int

    // Returns LiveData so Room can handle the threading
    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscribers(): LiveData<List<Subscriber>>
}