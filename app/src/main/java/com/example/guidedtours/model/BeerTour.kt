package com.example.guidedtours.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration

@Entity(tableName = "BeerTour")
data class BeerTour(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val duration: Float,
    val rating: Float,
    val isFavourite: Boolean,
    val breweryId: Int
)