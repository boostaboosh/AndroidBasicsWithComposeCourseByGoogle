package com.example.londonapp.ui.stateConsumers.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.londonapp.data.repositories.KidFriendlyPlacesRepository
import com.example.londonapp.data.repositories.ParksRepository
import com.example.londonapp.data.repositories.RestaurantsRepository
import com.example.londonapp.data.repositories.ShopsRepository
import com.example.londonapp.data.sources.local.KidFriendlyPlacesLocalDataSource
import com.example.londonapp.data.sources.local.ParksLocalDataSource
import com.example.londonapp.data.sources.local.RestaurantsLocalDataSource
import com.example.londonapp.data.sources.local.ShopsLocalDataSource
import com.example.londonapp.domain.GetKidFriendlyPlacesUseCase
import com.example.londonapp.domain.GetParksUseCase
import com.example.londonapp.domain.GetPlacesByCategoryUseCase
import com.example.londonapp.domain.GetRestaurantsUseCase
import com.example.londonapp.domain.GetShopsUseCase
import com.example.londonapp.ui.navigation.Destination
import com.example.londonapp.domain.PlaceCategory
import com.example.londonapp.ui.stateProducers.screenStateProducers.PlacesListScreenStateProducer

@Composable
fun PlacesListScreen(
    placeCategory: PlaceCategory,
    onListItemPressed: (placeId: Int) -> Unit,
    onTabSelected: (Destination) -> Unit,
    onBackPressed: () -> Unit,
) {
    // create view model dependencies
    val getPlacesByCategoryUseCase = remember {
        GetPlacesByCategoryUseCase(
            getParksUseCase = GetParksUseCase(ParksRepository(ParksLocalDataSource())),
            getRestaurantsUseCase = GetRestaurantsUseCase(RestaurantsRepository(RestaurantsLocalDataSource())),
            getShopsUseCase = GetShopsUseCase(ShopsRepository(ShopsLocalDataSource())),
            getKidFriendlyPlacesUseCase = GetKidFriendlyPlacesUseCase(KidFriendlyPlacesRepository(KidFriendlyPlacesLocalDataSource())),
        )
    }

    // create view model
    val placesListScreenStateProducer = remember {
        PlacesListScreenStateProducer(getPlacesByCategoryUseCase)
    }

    // retrieve state
    LaunchedEffect(Unit) {
        placesListScreenStateProducer.createState(placeCategory)
    }
    val placesListScreenState by placesListScreenStateProducer.uiState.collectAsState()
}
