package com.example.amilosevic.guessthecountry.data.roomDB

import androidx.room.*
import androidx.room.Dao
import com.example.amilosevic.guessthecountry.model.ResultDetails

@Dao
interface Dao {
    //add getId
    @Query("SELECT * FROM resultDetails")
    fun getUser(): ResultDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(resultDetails: ResultDetails)

    @Delete
    fun deleteUser(resultDetails: ResultDetails)
}