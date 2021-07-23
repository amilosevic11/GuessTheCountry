package com.example.amilosevic.guessthecountry.data.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.amilosevic.guessthecountry.model.UserId

@Dao
interface UserIdDao {

    @Query("SELECT * FROM userIds")
    fun getToken(): UserId?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userId: UserId)
}