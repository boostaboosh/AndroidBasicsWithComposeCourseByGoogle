package com.example.londonapp.ui.stateProducers.screenStateProducers

import androidx.lifecycle.ViewModel
import com.example.londonapp.R
import com.example.londonapp.data.sources.local.dataSourceModels.Park
import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace
import com.example.londonapp.data.sources.local.dataSourceModels.Restaurant
import com.example.londonapp.domain.IGetPlaceByIdUseCase
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

    private val _state: MutableStateFlow<PlaceDetailsScreenState> = MutableStateFlow(PlaceDetailsScreenState.Loading)
    internal val state: StateFlow<PlaceDetailsScreenState> = _state.asStateFlow()

    internal fun onCreate(placeId: Int) {
        try {
            val place = getPlaceByIdUseCase(placeId)
            _state.value = PlaceDetailsScreenState.Success(
                placeDetails = mapPlaceToDetails(place),
                showPreviousButton = false,
                showNextButton = place.pictureReferences.size > 1,
            )
        } catch (e: Exception) {
            _state.value = PlaceDetailsScreenState.Error(message = "Error loading place: ${e.message}.")
        }
    }

    internal fun onShowPreviousPicture() {
        // todo: update state to show previous picture and update which carousel buttons to show
        _state.update { currentState ->
            when(currentState) {
                is PlaceDetailsScreenState.Success -> {
                    currentState // todo: remove once pseudocode is implemented
                                /*
                    pseudocode:
                    when (getPreviousPictureUseCase(placeId = placeId) {
                        is Success -> {
                            val newPicture = Success.pictureData.newPictureReference
                            showPreviousButton = Success.pictureData // true if there are previous pics
                        }
                        is NoPreviousPicture -> { // this could indicate that there are no previous pics
                            showPreviousPicButton = false
                        }
                        is Error -> currentState
                    }
                     */
                }
                else -> currentState
            }
        }
    }

    internal fun onShowNextPicture() {
        // todo: update state to show next picture and update which carousel buttons to show
        _state.update { currentState ->
            when(currentState) {
                is PlaceDetailsScreenState.Success -> {
                    currentState // todo: remove once pseudocode is implemented
                                /*
                    pseudocode:
                    when (getNextPictureUseCase(placeId = placeId) {
                        is Success -> {
                            val newPicure = Success.pictureData.newPictureReference
                            showPreviousButton = Success.pictureData // true if there are more pics
                        }
                        is Failure -> {
                            currentState
                        }
                    }
                     */
                }
                else -> currentState
            }
        }
    }

    private fun mapPlaceToDetails(place: RecommendedPlace): PlaceDetails {
        return when (place) {
            is Restaurant -> PlaceDetails.Restaurant(
                name = place.name,
                pictureReference = getFirstPicture(place),
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
                pictureReference = getFirstPicture(place),
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
                pictureReference = getFirstPicture(place),
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
