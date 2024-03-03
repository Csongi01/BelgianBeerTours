package com.example.guidedtours.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.guidedtours.model.BeerTour
import kotlinx.coroutines.flow.Flow


@Dao
interface BeerTourDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBeerTour(beerTour: BeerTour)

    @Query("SELECT * FROM BeerTour order by id ASC")
    fun getBeerTours(): Flow<List<BeerTour>>

    @Query("SELECT * FROM BeerTour WHERE BeerTour.isFavourite =1")
    fun getFavorites(): Flow<List<BeerTour>>

    @Query("SELECT * FROM BeerTour where id=:beerTourId")
    fun getBeerTour(beerTourId: Int): Flow<BeerTour>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(beerTour: BeerTour)

    @Delete
    suspend fun delete(beerTour: BeerTour)

}