package com.example.guidedtours.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Brewery")
data class Brewery(
    @PrimaryKey(autoGenerate = true)
    val Id: Int ,
    val city: String,
    val tourGuide: String,
    val beerPrice: Float,
)
