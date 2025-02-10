package com.example.londonapp.ui.stateProducers.screenStateProducers

import androidx.lifecycle.ViewModel
import com.example.londonapp.domain.GetPlacesByCategoryUseCase
import com.example.londonapp.domain.PlaceCategory
import com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates.PlacesListScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlacesListScreenStateProducer(
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
                placesList = placesOfCategory
            )
        }
    }
}
