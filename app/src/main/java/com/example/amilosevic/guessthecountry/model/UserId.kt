package com.example.amilosevic.guessthecountry.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userIds")
data class UserId(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "userId")
    var userId: String
)