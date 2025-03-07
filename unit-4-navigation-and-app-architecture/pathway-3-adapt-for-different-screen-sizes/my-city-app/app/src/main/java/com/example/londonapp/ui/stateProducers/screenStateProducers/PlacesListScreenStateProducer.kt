package com.example.londonapp.ui.stateProducers.screenStateProducers

import androidx.lifecycle.ViewModel
import com.example.londonapp.data.sources.local.dataSourceModels.price.toCapitalisedString
import com.example.londonapp.domain.GetPlacesByCategoryUseCase
import com.example.londonapp.domain.PlaceCategory
import com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates.PlaceCardState
import com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates.PlacesListScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class PlacesListScreenStateProducer(
    private val getPlacesByCategoryUseCase: GetPlacesByCategoryUseCase,
): ViewModel() {

    //backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(PlacesListScreenState())
    val uiState: StateFlow<PlacesListScreenState> = _uiState.asStateFlow()

    fun createState(placeCategory: PlaceCategory) {
        // get places of provided category via use case
        val placesOfCategory = getPlacesByCategoryUseCase(placeCategory)
        // update placesListScreenState to include list of place of provided category
        _uiState.update { currentState ->
            currentState.copy(
                placesList = placesOfCategory.map { place ->
                    PlaceCardState(
                        placeId = place.id,
                        imageRes = place.pictureReferences.firstOrNull(),
                        name = place.name,
                        neighbourhood = place.location.neighbourhoodName,
                        cardinalLocation = place.location.cardinalCompassDirection,
                        affordabilityLevel = place.price.getAffordabilityLevel().toCapitalisedString(),
                    )
                }
            )
        }
    }
}
