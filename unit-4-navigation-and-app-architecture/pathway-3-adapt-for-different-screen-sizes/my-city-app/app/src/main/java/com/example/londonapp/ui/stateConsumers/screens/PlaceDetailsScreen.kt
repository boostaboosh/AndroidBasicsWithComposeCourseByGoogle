package com.example.londonapp.ui.stateConsumers.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.londonapp.data.repositories.KidFriendlyPlacesRepository
import com.example.londonapp.data.repositories.ParksRepository
import com.example.londonapp.data.repositories.RestaurantsRepository
import com.example.londonapp.data.repositories.ShopsRepository
import com.example.londonapp.data.sources.local.KidFriendlyPlacesLocalDataSource
import com.example.londonapp.data.sources.local.ParksLocalDataSource
import com.example.londonapp.data.sources.local.RestaurantsLocalDataSource
import com.example.londonapp.data.sources.local.ShopsLocalDataSource
import com.example.londonapp.domain.GetAllPlacesUseCase
import com.example.londonapp.domain.GetKidFriendlyPlacesUseCase
import com.example.londonapp.domain.GetParksUseCase
import com.example.londonapp.domain.GetPlaceByIdUseCase
import com.example.londonapp.domain.GetPlacesByCategoryUseCase
import com.example.londonapp.domain.GetRestaurantsUseCase
import com.example.londonapp.domain.GetShopsUseCase
import com.example.londonapp.domain.IGetPlaceByIdUseCase
import com.example.londonapp.ui.navigation.Destination
import com.example.londonapp.ui.stateProducers.screenStateProducers.PlaceDetailsScreenStateProducer
import com.example.londonapp.ui.stateProducers.screenStateProducers.PlacesListScreenStateProducer

@Composable
fun PlaceDetailsScreen(
    placeId: Int,
    onTabSelected: (Destination) -> Unit,
    onBackPressed: () -> Unit, /*todo: navigate to places list screen (only on compact and medium windows widths), is this automatic?*/
) {
    // create view model dependencies
    val getPlaceByIdUseCase = remember {
        GetPlaceByIdUseCase(
            getAllPlacesUseCase = GetAllPlacesUseCase(
                kidFriendlyPlacesUseCase = GetKidFriendlyPlacesUseCase(KidFriendlyPlacesRepository(KidFriendlyPlacesLocalDataSource())),
                parksUseCase = GetParksUseCase(ParksRepository(ParksLocalDataSource())),
                restaurantsUseCase = GetRestaurantsUseCase(RestaurantsRepository(RestaurantsLocalDataSource())),
                shopsUseCase = GetShopsUseCase(ShopsRepository(ShopsLocalDataSource())),
            )
        )
    }

    // create view model
    val placeDetailsScreenStateProducer = remember {
        PlaceDetailsScreenStateProducer(getPlaceByIdUseCase = getPlaceByIdUseCase)
    }

    // retrieve state
    LaunchedEffect(Unit) {
        placeDetailsScreenStateProducer.onCreate(placeId)
    }
    val placeDetailsScreenState by placeDetailsScreenStateProducer.state.collectAsState()
}
