package com.example.guidedtours.db_instance

import android.content.Context
import com.example.guidedtours.Repository.BeerTourRepository
import com.example.guidedtours.data.BeerTourDatabase

object Graph {


    lateinit var db : BeerTourDatabase
        private set

    val repository by lazy{
        BeerTourRepository(
            beerTourDao = db.beerTourDao(),
            breweryDao = db.breweryDao()
        )
    }


    fun provide(context: Context)
    {
        db = BeerTourDatabase.getDatabase(context)
    }
}