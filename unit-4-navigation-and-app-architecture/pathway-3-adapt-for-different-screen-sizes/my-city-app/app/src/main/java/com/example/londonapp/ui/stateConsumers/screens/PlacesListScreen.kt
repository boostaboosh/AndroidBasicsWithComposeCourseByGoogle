package com.example.londonapp.ui.stateConsumers.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
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
import com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates.PlacesListScreenState

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
        val windowWidth = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
        when (windowWidth) {
            WindowWidthSizeClass.COMPACT -> {
                Scaffold(
                    topBar = { TopBackBar(text = placeCategory.label, onBackPressed = onBackPressed) },
                ) { contentPadding ->
                    PlacesList(Modifier.padding(contentPadding), placesListScreenState, onListItemPressed)
                }
            }
            WindowWidthSizeClass.MEDIUM -> {
                PlacesList(placesListScreenState = placesListScreenState, onListItemPressed = onListItemPressed)
            }
            WindowWidthSizeClass.EXPANDED -> {
                Row {
                    var placeId by remember { mutableIntStateOf(placesListScreenState.placesList.firstOrNull()?.placeId ?: 1) }
                    PlacesList(
                        modifier = Modifier.weight(1f),
                        placesListScreenState = placesListScreenState, 
                        onListItemPressed = { placeId = it }
                    )
                    VerticalDivider(modifier = Modifier.fillMaxHeight(), thickness = 1.dp, color = Color.Black)
                    PlaceDetailsScreen(
                        modifier = Modifier.weight(1f),
                        placeId = placeId,
                        onBackPressed = onBackPressed,
                    )
                }
            }
        }
    }
}

@Composable
private fun PlacesList(
    modifier: Modifier = Modifier,
    placesListScreenState: PlacesListScreenState,
    onListItemPressed: (placeId: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item { Spacer(Modifier.padding(top = 0.dp)) }
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
        item { Spacer(Modifier.padding(top = 0.dp)) }
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
