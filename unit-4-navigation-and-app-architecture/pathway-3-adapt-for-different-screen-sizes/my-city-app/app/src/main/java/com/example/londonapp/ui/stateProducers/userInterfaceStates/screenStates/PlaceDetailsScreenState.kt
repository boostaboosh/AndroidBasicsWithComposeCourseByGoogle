package com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates

import androidx.annotation.DrawableRes

internal sealed class PlaceDetailsScreenState {

    data object Loading: PlaceDetailsScreenState()

    data class SuccessfulPlaceLoad(
        val placeDetails: PlaceDetails,
        @get:DrawableRes
        val pictureReference: Int,
        val showPreviousButton: Boolean,
        val showNextButton: Boolean,
    ): PlaceDetailsScreenState()

    data class Error(val message: String): PlaceDetailsScreenState()

}

internal sealed class PlaceDetails {

    abstract val name: String
    abstract val googleMapsLink: String
    abstract val cardinalCompassDirection: String
    abstract val neighbourhoodName: String
    abstract val bookingRequirement: String
    abstract val isRecommendedForKids: Boolean
    abstract val affordabilityLevel: String
    abstract val description: String

    data class Place(
        override val name: String,
        override val googleMapsLink: String,
        override val cardinalCompassDirection: String,
        override val neighbourhoodName: String,
        override val bookingRequirement: String,
        override val isRecommendedForKids: Boolean,
        override val affordabilityLevel: String,
        override val description: String,
    ) : PlaceDetails()

    data class Restaurant(
        override val name: String,
        override val googleMapsLink: String,
        override val cardinalCompassDirection: String,
        override val neighbourhoodName: String,
        override val bookingRequirement: String,
        override val isRecommendedForKids: Boolean,
        override val affordabilityLevel: String,
        override val description: String,
        val takesCard: Boolean,
        val cuisine: String,
        val recommendedByWordOfMouth: Boolean,
    ) : PlaceDetails()

    data class Park(
        override val name: String,
        override val googleMapsLink: String,
        override val cardinalCompassDirection: String,
        override val neighbourhoodName: String,
        override val bookingRequirement: String,
        override val isRecommendedForKids: Boolean,
        override val affordabilityLevel: String,
        override val description: String,
        val hasParkrun: Boolean,
        val hasUnpavedTrails: Boolean,
        val isRecommendedForPicnic: Boolean,
        val isCyclingFriendly: Boolean,
        val areaInHectares: String,
    ) : PlaceDetails()

}
