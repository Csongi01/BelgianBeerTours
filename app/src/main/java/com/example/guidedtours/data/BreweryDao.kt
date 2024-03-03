package com.example.guidedtours.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.example.guidedtours.model.Brewery

@Dao
interface BreweryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBrewery(brewery: Brewery)

    @Query("SELECT * FROM Brewery order by id ASC")
    fun getBreweries(): Flow<List<Brewery>>


    @Query("SELECT * FROM Brewery where id =:breweryId")
    fun getBrewery(breweryId: Int): Flow<Brewery>


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(brewery: Brewery)

    @Delete
    suspend fun delete(brewery: Brewery)

}