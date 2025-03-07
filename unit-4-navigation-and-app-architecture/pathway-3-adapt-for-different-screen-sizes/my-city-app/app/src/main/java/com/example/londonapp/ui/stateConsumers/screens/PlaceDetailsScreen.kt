package com.example.londonapp.ui.stateConsumers.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
import com.example.londonapp.domain.GetRestaurantsUseCase
import com.example.londonapp.domain.GetShopsUseCase
import com.example.londonapp.ui.stateConsumers.components.TopBackBar
import com.example.londonapp.ui.stateConsumers.theme.Typography
import com.example.londonapp.ui.stateProducers.screenStateProducers.PlaceDetailsScreenStateProducer
import com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates.PlaceDetails
import com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates.PlaceDetailsScreenState

@Composable
fun PlaceDetailsScreen(
    placeId: Int,
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
    val placeDetailsScreenStateProducer = remember { PlaceDetailsScreenStateProducer(getPlaceByIdUseCase = getPlaceByIdUseCase) }

    // retrieve state
    LaunchedEffect(Unit) {
        placeDetailsScreenStateProducer.onCreate(placeId)
    }
    val placeDetailsScreenState by placeDetailsScreenStateProducer.state.collectAsState()

    when (val currentState = placeDetailsScreenState) {
        is PlaceDetailsScreenState.Loading -> Text("Loading place details...", style = Typography.labelLarge)
        is PlaceDetailsScreenState.Error -> Text(currentState.message, style = Typography.labelLarge, color = Color.Red)
        is PlaceDetailsScreenState.Success -> PlaceDetailsScreenContent(
            placeDetails = currentState.placeDetails,
            onBackPressed = onBackPressed,
            showPreviousButton = currentState.showPreviousButton,
            onShowPreviousImageClicked = { placeDetailsScreenStateProducer.onShowPreviousPictureClicked() },
            showNextButton = currentState.showNextButton,
            onShowNextImageClicked = { placeDetailsScreenStateProducer.onShowNextPictureClicked() },
        )
    }
}

@Composable
fun ImageCarousel(
    @DrawableRes image: Int,
    contentDescription: String,
    showPreviousButton: Boolean,
    onPreviousClicked: () -> Unit,
    showNextButton: Boolean,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    // todo
}

@Composable
private fun PlaceDetailsScreenContent(
    placeDetails: PlaceDetails,
    onBackPressed: () -> Unit,
    showPreviousButton: Boolean,
    onShowPreviousImageClicked: () -> Unit,
    showNextButton: Boolean,
    onShowNextImageClicked: () -> Unit,
) {
    Scaffold(
        topBar = { TopBackBar(text = placeDetails.name, onBackPressed = onBackPressed) }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding).padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ImageCarousel(
                image = placeDetails.pictureReference,
                contentDescription = "Image of ${placeDetails.name}.",
                showPreviousButton = showPreviousButton,
                onPreviousClicked = onShowPreviousImageClicked,
                showNextButton = showNextButton,
                onNextClicked = onShowNextImageClicked,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(text = "Details", style = Typography.headlineLarge)
            GenericPlaceDetals(placeDetails)
            when(placeDetails) {
                is PlaceDetails.Restaurant -> RestaurantDetailsSection(placeDetails)
                is PlaceDetails.Park -> ParksDetailsSection(placeDetails)
                is PlaceDetails.Place -> Unit
            }
            MapsButton(mapsLink = placeDetails.googleMapsLink) // todo: fix this at bottom of screen outside of scrolling
        }
    }
}

@Composable
private fun ParksDetailsSection(placeDetails: PlaceDetails.Park) {
    // todo
}

@Composable
private fun RestaurantDetailsSection(placeDetails: PlaceDetails.Restaurant) {
    // todo
}

@Composable
private fun GenericPlaceDetals(placeDetails: PlaceDetails) {
    DetailTextRow(field = "place name", value = placeDetails.name)
    LocationInfoSection(placeDetails)
    BookingInfoSection(placeDetails)
    DetailYesNoRow(field = "is recommended for kids", value = placeDetails.isRecommendedForKids)
    Description(text = placeDetails.description)
}

@Composable
private fun Description(text: String) {
    // todo
}

@Composable
private fun BookingInfoSection(placeDetails: PlaceDetails) {
    Text("booking information", style = Typography.headlineSmall)
    DetailTextRow(field = "booking requirement", value = placeDetails.bookingRequirement)
    DetailTextRow(field = "affordability level", value = placeDetails.affordabilityLevel)
}

@Composable
private fun LocationInfoSection(placeDetails: PlaceDetails) {
    Text("Location information", style = Typography.headlineSmall)
    DetailTextRow(field = "cardinal compass direction", value = placeDetails.cardinalCompassDirection)
    DetailTextRow(field = "neighbourhood", value = placeDetails.neighbourhoodName)
}

@Composable
private fun DetailYesNoRow(field: String, value: Boolean) {
    // todo
}

@Composable
private fun DetailTextRow(field: String, value: String) {
    // todo
}

@Composable
private fun MapsButton(mapsLink: String) {
    // todo
}
