package com.example.londonapp.ui.stateConsumers.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace
import com.example.londonapp.ui.navigation.Destination
import com.example.londonapp.ui.navigation.PlaceCategory
import com.example.londonapp.ui.stateProducers.ScreenStateProducers.PlaceDetailsScreenStateProducer

@Composable
fun PlacesListScreen(
    placeCategory: PlaceCategory,
    onListItemPressed: (RecommendedPlace) -> Unit,
    onTabSelected: (Destination) -> Unit,
    onBackPressed: () -> Unit,
    placeDetailsScreenStateProducer: PlaceDetailsScreenStateProducer = viewModel(),
) {
    // retrieve state
    val placesListScreenState by placeDetailsScreenStateProducer.uiState
}