package com.example.guidedtours.ui.Favourite

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


class FavouriteViewModel( private val repository : BeerTourRepository = Graph.repository):
    ViewModel(){
    var state by mutableStateOf(FavouriteState())
        private set

    init{
        getFavourites()

    }

    private fun getFavourites()
    {
        viewModelScope.launch {
            repository.getfavourites().collectLatest{
                state = state.copy(
                    favourites = it
                )
            }
        }
    }

    fun deleteBeerTour(beerTour: BeerTour)
    {
        viewModelScope.launch {
            repository.deleteBeerTour(beerTour)
        }
    }
}

data class FavouriteState(
    val favourites: List<BeerTour> = emptyList()
)