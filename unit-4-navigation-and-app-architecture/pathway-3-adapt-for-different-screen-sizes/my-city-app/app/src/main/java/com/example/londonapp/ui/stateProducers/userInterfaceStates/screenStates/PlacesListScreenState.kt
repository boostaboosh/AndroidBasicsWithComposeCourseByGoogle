package com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates

import androidx.annotation.DrawableRes

data class PlacesListScreenState (
    val placesList: List<PlaceCardState> = emptyList()
)

data class PlaceCardState (
    val placeId: Int,
    @DrawableRes val imageRes: Int?,
    val name: String,
    val neighbourhood: String,
    val cardinalLocation: String,
    val affordabilityLevel: String,
)
