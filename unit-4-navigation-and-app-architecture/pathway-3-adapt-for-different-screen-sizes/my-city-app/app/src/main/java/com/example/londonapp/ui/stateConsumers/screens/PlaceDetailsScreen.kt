package com.example.londonapp.ui.stateConsumers.screens

import androidx.compose.runtime.Composable
import com.example.londonapp.ui.navigation.Destination

@Composable
fun PlaceDetailsScreen(
    placeId: Int,
    onTabSelected: (Destination) -> Unit,
    onBackPressed: () -> Unit, /*todo: navigate to places list screen (only on compact and medium windows widths), is this automatic?*/
) {
}
