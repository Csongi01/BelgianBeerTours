package com.example.guidedtours.ui.Detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guidedtours.db_instance.Graph
import com.example.guidedtours.Repository.BeerTourRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class DetailViewModel(
    private val repository: BeerTourRepository = Graph.repository
) : ViewModel() {
    var detailState by mutableStateOf(DetailState())
        private set
    init {
        getBrewery()
    }

    private fun getBrewery() {
        viewModelScope.launch {
                repository.getInfoBrewery().collectLatest {
                    detailState = detailState.copy(
                        city = it.city,
                        tourGuide = it.tourGuide,
                        beerPrice = it.beerPrice
                    )
                }
            }
        }
    }

data class DetailState(
    val city: String ="",
    val tourGuide: String ="",
    val beerPrice: Float = 0.0f,
)
