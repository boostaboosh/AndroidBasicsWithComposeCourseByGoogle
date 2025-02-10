package com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates

import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace

data class PlacesListScreenState (
    val placesList: List<RecommendedPlace> = emptyList()
)
