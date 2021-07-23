package com.example.amilosevic.guessthecountry.model

import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "resultDetails")
@Parcelize
data class ResultDetails(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "username")
    var username: String? = null,
    @ColumnInfo(name = "score")
    var score: String? = null,
    @ColumnInfo(name = "date")
    var date: Date? = null,
    @ColumnInfo(name = "imageURL")
    var imageURL: Uri? = null
) : Parcelable
