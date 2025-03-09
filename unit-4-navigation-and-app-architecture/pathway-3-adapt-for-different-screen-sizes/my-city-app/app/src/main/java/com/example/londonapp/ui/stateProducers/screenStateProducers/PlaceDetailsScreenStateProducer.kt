package com.example.londonapp.ui.stateProducers.screenStateProducers

import androidx.lifecycle.ViewModel
import com.example.londonapp.R
import com.example.londonapp.data.sources.local.dataSourceModels.Park
import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace
import com.example.londonapp.data.sources.local.dataSourceModels.Restaurant
import com.example.londonapp.domain.GetNextPictureResult
import com.example.londonapp.domain.GetPreviousPictureResult
import com.example.londonapp.domain.IGetNextPictureUseCase
import com.example.londonapp.domain.IGetPlaceByIdUseCase
import com.example.londonapp.domain.IGetPreviousPictureUseCase
import com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates.PlaceDetails
import com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates.PlaceDetailsScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale

internal class PlaceDetailsScreenStateProducer(
    private val getPlaceByIdUseCase: IGetPlaceByIdUseCase,
    private val getPreviousPictureUseCase: IGetPreviousPictureUseCase,
    private val getNextPictureUseCase: IGetNextPictureUseCase,
): ViewModel() {
    private var placeId: Int = 0

    private val _state: MutableStateFlow<PlaceDetailsScreenState> = MutableStateFlow(PlaceDetailsScreenState.Loading)
    internal val state: StateFlow<PlaceDetailsScreenState> = _state.asStateFlow()

    internal fun onCreate(placeId: Int) {
        this.placeId = placeId
        try {
            val place = getPlaceByIdUseCase(placeId)
            _state.value = PlaceDetailsScreenState.SuccessfulPlaceLoad(
                placeDetails = mapPlaceToDetails(place),
                pictureReference = getFirstPicture(place),
                showPreviousButton = false,
                showNextButton = place.pictureReferences.size > 1,
            )
        } catch (e: Exception) {
            _state.value = PlaceDetailsScreenState.Error(message = "Error loading place: ${e.message}.")
        }
    }

    internal fun onShowPreviousPicture() {
        _state.update { currentState ->
            when(currentState) {
                is PlaceDetailsScreenState.SuccessfulPlaceLoad -> {
                    when(val result = getPreviousPictureUseCase(placeId, currentState.pictureReference)) {
                        is GetPreviousPictureResult.Success -> {
                            currentState.copy(
                                pictureReference = result.previousPic,
                                showPreviousButton = !result.isFirstPicture,
                                showNextButton = true,
                            )
                        }
                        is GetPreviousPictureResult.Error -> {
                            currentState
                        }
                    }
                }
                else -> currentState
            }
        }
    }

    internal fun onShowNextPicture() {
        _state.update { currentState ->
            when(currentState) {
                is PlaceDetailsScreenState.SuccessfulPlaceLoad -> {
                    when(val result = getNextPictureUseCase(placeId, currentState.pictureReference)) {
                        is GetNextPictureResult.Success -> {
                            currentState.copy(
                                pictureReference = result.nextPic,
                                showPreviousButton = true,
                                showNextButton = !result.isLastPicture,
                            )
                        }
                        is GetNextPictureResult.Error -> {
                            currentState
                        }
                    }
                }
                else -> currentState
            }
        }
    }

    private fun mapPlaceToDetails(place: RecommendedPlace): PlaceDetails {
        return when (place) {
            is Restaurant -> PlaceDetails.Restaurant(
                name = place.name,
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
                areaInHectares = "%.2f".format(place.areaInHectares),
            )
            else -> PlaceDetails.Place(
                name = place.name,
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

    private fun getFirstPicture(place: RecommendedPlace): Int {
        return if(place.pictureReferences.isEmpty()) R.drawable.baseline_no_photography_24
        else place.pictureReferences.first()
    }

}

private fun String.toTitle() = lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
