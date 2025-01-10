package com.example.londonapp.ui.stateProducers.ScreenStateProducers

import androidx.lifecycle.ViewModel
import com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates.PlacesListScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlacesListScreenStateProducer(): ViewModel() {
    //backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(PlacesListScreenState())
    val uiState: StateFlow<PlacesListScreenState> = _uiState.asStateFlow()
}