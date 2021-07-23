package com.example.amilosevic.guessthecountry.data.roomDB

import androidx.room.Room
import com.example.amilosevic.guessthecountry.GuessTheCountryApp

object DatabaseBuilder {
    private var instance: Database? = null
    fun getInstance(): Database {
        synchronized(Database::class) {
            if(instance == null)
                instance = buildDatabase()
        }
        return instance!!
    }

    private fun buildDatabase() : Database {
        return Room.databaseBuilder(GuessTheCountryApp.context, Database::class.java, Database.NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}