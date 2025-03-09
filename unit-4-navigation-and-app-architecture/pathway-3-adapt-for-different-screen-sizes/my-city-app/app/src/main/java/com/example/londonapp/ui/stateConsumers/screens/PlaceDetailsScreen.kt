package com.example.londonapp.ui.stateConsumers.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
import com.example.londonapp.domain.GetNextPictureUseCase
import com.example.londonapp.domain.GetParksUseCase
import com.example.londonapp.domain.GetPlaceByIdUseCase
import com.example.londonapp.domain.GetPreviousPictureUseCase
import com.example.londonapp.domain.GetRestaurantsUseCase
import com.example.londonapp.domain.GetShopsUseCase
import com.example.londonapp.ui.stateConsumers.components.TopBackBar
import com.example.londonapp.ui.stateConsumers.theme.LondonAppTheme
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
                kidFriendlyPlacesUseCase = GetKidFriendlyPlacesUseCase(KidFriendlyPlacesRepository(KidFriendlyPlacesLocalDataSource)),
                parksUseCase = GetParksUseCase(ParksRepository(ParksLocalDataSource)),
                restaurantsUseCase = GetRestaurantsUseCase(RestaurantsRepository(RestaurantsLocalDataSource)),
                shopsUseCase = GetShopsUseCase(ShopsRepository(ShopsLocalDataSource)),
            )
        )
    }

    val getPreviousPictureUseCase = remember {
        GetPreviousPictureUseCase(
            getPlaceByIdUseCase = getPlaceByIdUseCase
        )
    }

    val getNextPictureUseCase = remember {
        GetNextPictureUseCase(
            getPlaceByIdUseCase = getPlaceByIdUseCase
        )
    }

    // create view model
    val placeDetailsScreenStateProducer = remember {
        PlaceDetailsScreenStateProducer(
            getPlaceByIdUseCase = getPlaceByIdUseCase,
            getPreviousPictureUseCase = getPreviousPictureUseCase,
            getNextPictureUseCase = getNextPictureUseCase,
        ) 
    }

    // retrieve state
    LaunchedEffect(Unit) {
        placeDetailsScreenStateProducer.onCreate(placeId)
    }
    val placeDetailsScreenState by placeDetailsScreenStateProducer.state.collectAsState()

    when (val currentState = placeDetailsScreenState) {
        is PlaceDetailsScreenState.Loading -> Text("Loading place details...", style = Typography.labelLarge)
        is PlaceDetailsScreenState.Error -> Text(currentState.message, style = Typography.labelLarge, color = Color.Red)
        is PlaceDetailsScreenState.SuccessfulPlaceLoad -> PlaceDetailsScreenContent(
            onBackPressed = onBackPressed,
            placeDetails = currentState.placeDetails,
            pictureReference = currentState.pictureReference,
            showPreviousButton = currentState.showPreviousButton,
            onShowPreviousImageClicked = { placeDetailsScreenStateProducer.onShowPreviousPicture() },
            showNextButton = currentState.showNextButton,
            onShowNextImageClicked = { placeDetailsScreenStateProducer.onShowNextPicture() },
        )
    }
}

@Composable
private fun PlaceDetailsScreenContent(
    onBackPressed: () -> Unit,
    placeDetails: PlaceDetails,
    @DrawableRes pictureReference: Int,
    showPreviousButton: Boolean,
    onShowPreviousImageClicked: () -> Unit,
    showNextButton: Boolean,
    onShowNextImageClicked: () -> Unit,
) {
    Scaffold(
        topBar = { TopBackBar(text = placeDetails.name, onBackPressed = onBackPressed) }
    ) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            LazyColumn(
                modifier = Modifier.weight(1f).padding(horizontal = 12.dp),
            ) {
                item {
                    Column(
                        modifier = Modifier.padding(top = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        ImageCarousel(
                            image = pictureReference,
                            contentDescription = "Image of ${placeDetails.name}.",
                            showPreviousButton = showPreviousButton,
                            onPreviousClicked = onShowPreviousImageClicked,
                            showNextButton = showNextButton,
                            onNextClicked = onShowNextImageClicked,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        GenericPlaceDetails(placeDetails)
                        when(placeDetails) {
                            is PlaceDetails.Restaurant -> RestaurantDetailsSection(placeDetails)
                            is PlaceDetails.Park -> ParksDetailsSection(placeDetails)
                            is PlaceDetails.Place -> Unit
                        }
                        Description(text = placeDetails.description)
                    }
                }
            }
            HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.Black)
            MapsButton(mapsLink = placeDetails.googleMapsLink, modifier = Modifier.align(Alignment.End))
        }
    }
}

@Composable
private fun ImageCarousel(
    @DrawableRes image: Int,
    contentDescription: String,
    showPreviousButton: Boolean,
    onPreviousClicked: () -> Unit,
    showNextButton: Boolean,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = contentDescription,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .border(
                    border = BorderStroke(1.dp, Color(0xFF000000)),
                    shape = RoundedCornerShape(12.dp)
                )
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
        if (showPreviousButton) {
            IconButton(
                onClick = onPreviousClicked,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp)
                    .clip(shape = RoundedCornerShape(100))
                    .background(Color(0x77000000)),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "show previous image",
                    tint = Color(0xFFFFFFFF),
                )
            }
        }
        if (showNextButton) {
            IconButton(
                onClick = onNextClicked,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp)
                    .clip(shape = RoundedCornerShape(100))
                    .background(Color(0x77000000)),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "show previous image",
                    tint = Color(0xFFFFFFFF),
                )
            }
        }
    }
}

@Composable
private fun GenericPlaceDetails(placeDetails: PlaceDetails) {
    Column {
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(color = 0xFF000000))
        Text(text = "Details", fontWeight = FontWeight(800), style = Typography.headlineLarge)
        DetailTextRow(field = "place name", value = placeDetails.name)
        LocationInfoSection(placeDetails, modifier = Modifier.padding(top = 8.dp))
        BookingInfoSection(placeDetails, modifier = Modifier.padding(top = 8.dp))
        DetailYesNoRow(field = "is recommended for kids", value = placeDetails.isRecommendedForKids)
    }
}

@Composable
private fun RestaurantDetailsSection(placeDetails: PlaceDetails.Restaurant) {
    Column {
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(color = 0xFF000000))
        Text("Restaurant specific details", fontWeight = FontWeight(800), style = Typography.headlineSmall)
        DetailYesNoRow(field = "take card", value = placeDetails.takesCard)
        DetailTextRow(field = "cuisine", value = placeDetails.cuisine)
        DetailYesNoRow(field = "recommended by word of mouth", value = placeDetails.recommendedByWordOfMouth)
    }
}

@Composable
private fun ParksDetailsSection(placeDetails: PlaceDetails.Park) {
    Column {
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(color = 0xFF000000))
        Text("Park specific details", fontWeight = FontWeight(800), style = Typography.headlineSmall)
        DetailYesNoRow(field = "has Parkrun", value = placeDetails.hasParkrun)
        DetailYesNoRow(field = "has unpaved trails", value = placeDetails.hasUnpavedTrails)
        DetailYesNoRow(field = "is recommended for picnics", value = placeDetails.isRecommendedForPicnic)
        DetailYesNoRow(field = "is cycling friendly", value = placeDetails.isCyclingFriendly)
        DetailTextRow(field = "area in hectares", value = placeDetails.areaInHectares)
    }
}

@Composable
private fun Description(text: String) {
    Column {
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(color = 0xFF000000))
        Text(
            text = buildAnnotatedString {
                withStyle(style = Typography.headlineSmall.copy(fontWeight = FontWeight(800)).toSpanStyle()) {
                    append("Description: ")
                }
                withStyle(style = Typography.bodyMedium.toSpanStyle()) {
                    append(text)
                }
            }
        )
    }
}

@Composable
private fun BookingInfoSection(placeDetails: PlaceDetails, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(color = 0xFF000000))
        Text("booking information", fontWeight = FontWeight(800), style = Typography.headlineSmall)
        DetailTextRow(field = "booking requirement", value = placeDetails.bookingRequirement)
        DetailTextRow(field = "affordability level", value = placeDetails.affordabilityLevel)
    }
}

@Composable
private fun LocationInfoSection(placeDetails: PlaceDetails, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color(color = 0xFF000000))
        Text("Location information", fontWeight = FontWeight(800), style = Typography.headlineSmall)
        DetailTextRow(field = "cardinal compass direction", value = placeDetails.cardinalCompassDirection)
        DetailTextRow(field = "neighbourhood", value = placeDetails.neighbourhoodName)
    }
}

@Composable
private fun DetailYesNoRow(field: String, value: Boolean) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = field, style = Typography.bodyMedium, fontWeight = FontWeight(800), modifier = Modifier.padding(end = 4.dp))
        Icon(
            imageVector = if (value) Icons.Default.Check else Icons.Default.Close,
            contentDescription = if (value) "yes" else "no",
        )
    }
}

@Composable
private fun DetailTextRow(field: String, value: String) {
    Row {
        Text(field, style = Typography.bodyMedium, fontWeight = FontWeight(800), modifier = Modifier.padding(end = 12.dp))
        Text(value, style = Typography.bodyMedium)
    }
}

@Composable
private fun MapsButton(mapsLink: String, modifier: Modifier = Modifier) {
    Button(
        onClick = {}, // todo: open google maps
        modifier = modifier,
    ) {
        Text("open in Google Maps")
    }
}

@DevicesPreview
@Composable
private fun PlaceDetailsScreenPreview() = LondonAppTheme { PlaceDetailsScreen(1, onBackPressed = {}) }
