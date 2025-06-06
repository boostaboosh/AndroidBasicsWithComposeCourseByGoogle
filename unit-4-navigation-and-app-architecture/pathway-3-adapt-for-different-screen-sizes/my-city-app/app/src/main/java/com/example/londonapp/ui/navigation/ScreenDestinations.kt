package com.example.londonapp.ui.navigation

import com.example.londonapp.domain.PlaceCategory
import kotlinx.serialization.Serializable

sealed interface Destination

@Serializable
class Home() : Destination

@Serializable
class RecommendedPlacesList(
    val category: PlaceCategory
) : Destination

@Serializable
class PlaceDetails(
    val placeId: Int
) : Destination
