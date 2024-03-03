package com.example.guidedtours.ui.BeerTour

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guidedtours.db_instance.Graph
import com.example.guidedtours.Repository.BeerTourRepository
import com.example.guidedtours.model.BeerTour
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class BeerTourViewModel( private val repository : BeerTourRepository = Graph.repository):
    ViewModel(){
    var state by mutableStateOf(TourState())
        private set


    init{
        getTours()

    }

    private fun getTours()
    {
        viewModelScope.launch {
            repository.getBeerTours().collectLatest{
                state = state.copy(
                    tours = it
                )
            }
        }
    }

    fun MakeFavourite(beerTour: BeerTour)
    {
            viewModelScope.launch {
                repository.updateBeerTour(
                    beerTour = beerTour.copy(
                        isFavourite = !beerTour.isFavourite
                    )

                )
            }
    }
    fun SelectedBeerTour(beerTour: BeerTour)
    {
        repository.breweryId = beerTour.breweryId
    }

    fun deleteBeerTour(beerTour: BeerTour)
    {
        viewModelScope.launch {
            repository.deleteBeerTour(beerTour)
        }
    }
}

data class TourState(
    val tours: List<BeerTour> = emptyList(),
    var favourite: Boolean = false
)