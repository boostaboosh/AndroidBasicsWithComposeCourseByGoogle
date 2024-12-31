package com.example.londonapp.ui.stateConsumers.screens

import androidx.compose.runtime.Composable
import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace
import com.example.londonapp.ui.navigation.Destination
import com.example.londonapp.ui.navigation.PlaceCategory

@Composable
fun PlacesListScreen(
    placeCategory: PlaceCategory,
    onListItemPressed: (RecommendedPlace) -> Unit,
    onTabSelected: (Destination) -> Unit,
    onBackPressed: () -> Unit,
) {
}