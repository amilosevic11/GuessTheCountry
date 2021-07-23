package com.example.amilosevic.guessthecountry.data.roomDB

import androidx.databinding.adapters.Converters
import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.amilosevic.guessthecountry.model.ResultDetails
import com.example.amilosevic.guessthecountry.model.UserId

@Database(entities = [ResultDetails::class, UserId::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun dao(): Dao
    abstract fun userId(): UserId

    companion object {
        const val NAME = "database"
    }
}