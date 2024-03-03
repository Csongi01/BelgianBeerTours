package com.example.guidedtours.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.guidedtours.model.BeerTour
import com.example.guidedtours.model.Brewery


@Database(entities = [Brewery::class, BeerTour::class], version = 2 ,exportSchema = false)

abstract class BeerTourDatabase: RoomDatabase() {
    abstract fun breweryDao(): BreweryDao
    abstract fun beerTourDao(): BeerTourDao


    companion object {
        @Volatile
        var INSTANCE: BeerTourDatabase? = null

        fun getDatabase(context: Context): BeerTourDatabase
        {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    BeerTourDatabase::class.java,
                    "beer_tour_database"
                ).createFromAsset("database/BeerToursBelgium.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}






