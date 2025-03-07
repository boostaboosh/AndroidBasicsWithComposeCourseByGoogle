package com.example.londonapp.ui.stateProducers.screenStateProducers

import androidx.lifecycle.ViewModel
import com.example.londonapp.data.sources.local.dataSourceModels.Park
import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace
import com.example.londonapp.data.sources.local.dataSourceModels.Restaurant
import com.example.londonapp.domain.IGetPlaceByIdUseCase
import com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates.PlaceDetails
import com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates.PlaceDetailsScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

internal class PlaceDetailsScreenStateProducer(
    private val getPlaceByIdUseCase: IGetPlaceByIdUseCase,
): ViewModel() {

    private val _state: MutableStateFlow<PlaceDetailsScreenState> = MutableStateFlow(PlaceDetailsScreenState.Loading)
    internal val state: StateFlow<PlaceDetailsScreenState> = _state.asStateFlow()

    internal fun onCreate(placeId: Int) {
        try {
            val place = getPlaceByIdUseCase(placeId)
            _state.value = PlaceDetailsScreenState.Success(placeDetails = mapPlaceToDetails(place))
        } catch (e: Exception) {
            _state.value = PlaceDetailsScreenState.Error(message = "Error loading place: ${e.message}.")
        }
    }

    private fun mapPlaceToDetails(place: RecommendedPlace): PlaceDetails {
        return when (place) {
            is Restaurant -> PlaceDetails.Restaurant(
                name = place.name,
                pictureReferences = place.pictureReferences.clone(),
                googleMapsLink = place.location.googleMapsLink,
                cardinalCompassDirection = place.location.cardinalCompassDirection,
                neighbourhoodName = place.location.neighbourhoodName,
                bookingRequirement = place.bookingRequirement.name.toTitle(),
                isRecommendedForKids = place.isRecommendedForKids,
                affordabilityLevel = place.price.getAffordabilityLevel().name.toTitle(),
                description = place.description,
                takesCard = place.takesCard,
                cuisine = place.cuisine,
                recommendedByWordOfMouth = place.recommendedByWordOfMouth,
            )
            is Park -> PlaceDetails.Park(
                name = place.name,
                pictureReferences = place.pictureReferences.clone(),
                googleMapsLink = place.location.googleMapsLink,
                cardinalCompassDirection = place.location.cardinalCompassDirection,
                neighbourhoodName = place.location.neighbourhoodName,
                bookingRequirement = place.bookingRequirement.name.toTitle(),
                isRecommendedForKids = place.isRecommendedForKids,
                affordabilityLevel = place.price.getAffordabilityLevel().name.toTitle(),
                description = place.description,
                hasParkrun = place.hasParkrun,
                hasUnpavedTrails = place.hasUnpavedTrails,
                isRecommendedForPicnic = place.isRecommendedForPicnic,
                isCyclingFriendly = place.isCyclingFriendly,
                areaInHectares = place.areaInHectares,
            )
            else -> PlaceDetails.Place(
                name = place.name,
                pictureReferences = place.pictureReferences.clone(),
                googleMapsLink = place.location.googleMapsLink,
                cardinalCompassDirection = place.location.cardinalCompassDirection,
                neighbourhoodName = place.location.neighbourhoodName,
                bookingRequirement = place.bookingRequirement.name.toTitle(),
                isRecommendedForKids = place.isRecommendedForKids,
                affordabilityLevel = place.price.getAffordabilityLevel().name.toTitle(),
                description = place.description,
            )
        }
    }

}

private fun String.toTitle() = lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
