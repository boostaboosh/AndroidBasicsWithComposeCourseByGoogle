package com.example.londonapp.ui.stateConsumers.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
import com.example.londonapp.domain.PlaceCategory
import com.example.londonapp.ui.navigation.AdaptiveNavigation
import com.example.londonapp.ui.navigation.Destination
import com.example.londonapp.ui.navigation.RecommendedPlacesList
import com.example.londonapp.ui.stateConsumers.components.PlaceCard
import com.example.londonapp.ui.stateConsumers.components.TopBackBar
import com.example.londonapp.ui.stateConsumers.theme.LondonAppTheme
import com.example.londonapp.ui.stateProducers.screenStateProducers.PlacesListScreenStateProducer

@Composable
fun PlacesListScreen(
    placeCategory: PlaceCategory,
    onListItemPressed: (placeId: Int) -> Unit, /*todo: navigate to places details screen (only on compact and medium window widths)*/
    onTabSelected: (Destination) -> Unit,
    onBackPressed: () -> Unit,
) {
    // create view model dependencies
    val getPlacesByCategoryUseCase = remember {
        GetPlacesByCategoryUseCase(
            getParksUseCase = GetParksUseCase(ParksRepository(ParksLocalDataSource)),
            getRestaurantsUseCase = GetRestaurantsUseCase(RestaurantsRepository(RestaurantsLocalDataSource)),
            getShopsUseCase = GetShopsUseCase(ShopsRepository(ShopsLocalDataSource)),
            getKidFriendlyPlacesUseCase = GetKidFriendlyPlacesUseCase(KidFriendlyPlacesRepository(KidFriendlyPlacesLocalDataSource)),
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

    AdaptiveNavigation(
        selectedTab = RecommendedPlacesList(category = placeCategory),
        onTabSelected = onTabSelected,
    ) {
        Scaffold(
            topBar = { TopBackBar(text = placeCategory.label, onBackPressed = onBackPressed) },
        ) { contentPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                item {Spacer(Modifier.padding(top = 0.dp)) }
                items(placesListScreenState.placesList) { place ->
                    PlaceCard(
                        onClick = { onListItemPressed(place.placeId) },
                        imageRes = place.imageRes,
                        name = place.name,
                        neighbourhood = place.neighbourhood,
                        cardinalLocation = place.cardinalLocation,
                        affordabilityLevel = place.affordabilityLevel,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                item {Spacer(Modifier.padding(top = 0.dp)) }
            }
        }
    }
}

@DevicesPreview
@Composable
private fun PlacesListScreenPreview() {
    LondonAppTheme { PlacesListScreen(
        placeCategory = PlaceCategory.PARKS,
        onBackPressed = {},
        onListItemPressed = {},
        onTabSelected = {},
    ) }
}
