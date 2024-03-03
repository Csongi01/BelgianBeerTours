package com.example.guidedtours.Repository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.guidedtours.data.BeerTourDao
import com.example.guidedtours.data.BreweryDao
import com.example.guidedtours.model.BeerTour
import com.example.guidedtours.model.Brewery
import kotlinx.coroutines.flow.Flow

class BeerTourRepository( private  val  beerTourDao: BeerTourDao,
                          private val breweryDao: BreweryDao)
{
    val allTours = beerTourDao.getBeerTours()
    val allBrewerys = breweryDao.getBreweries()
    val favourites = beerTourDao.getFavorites()

    suspend fun addBeerTour(beerTour: BeerTour)
    {
        beerTourDao.insertBeerTour(beerTour)
    }

    fun getBeerTours() = allTours
    fun getBrewerys() = allBrewerys
    fun getfavourites() = favourites

    fun getBrewery(breweryId: Int): Flow<Brewery>{
       return breweryDao.getBrewery(breweryId)
    }
    var breweryId by mutableStateOf(-1)

    fun getInfoBrewery():  Flow<Brewery>
    {
        return getBrewery(breweryId)
    }



    suspend fun addBrewery(brewery: Brewery)
    {
        breweryDao.insertBrewery(brewery)
    }

    suspend fun deleteBeerTour(beerTour: BeerTour)
    {
        beerTourDao.delete(beerTour)
    }

    suspend fun updateBeerTour(beerTour: BeerTour)
    {
        beerTourDao.update(beerTour)
    }

}