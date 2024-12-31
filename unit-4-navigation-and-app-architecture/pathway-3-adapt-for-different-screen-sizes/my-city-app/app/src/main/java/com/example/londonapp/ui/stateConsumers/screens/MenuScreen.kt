package com.example.londonapp.ui.stateConsumers.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.londonapp.R
import com.example.londonapp.ui.navigation.AdaptiveNavigation
import com.example.londonapp.ui.navigation.Destination
import com.example.londonapp.ui.navigation.Home
import com.example.londonapp.ui.navigation.PlaceCategory
import com.example.londonapp.ui.navigation.RecommendedPlacesList
import com.example.londonapp.ui.stateConsumers.theme.LondonAppTheme

@Composable
fun HomeScreen(
    onTabSelected: (Destination) -> Unit,
    onCategorySelected: (PlaceCategory) -> Unit,
) {
    AdaptiveNavigation(
        selectedTab = Home(),
        onTabSelected = onTabSelected,
    ) {
        MenuScreenContent(
            onCategorySelected = onCategorySelected,
        )
    }
}

@Composable
fun  MenuScreenContent(
    onCategorySelected: (PlaceCategory) -> Unit,
) {
    PlacesCategoriesList(
        categoriesList = PlaceCategory.entries,
        onCategorySelected = onCategorySelected,
    )
}

@Composable
fun PlacesCategoriesList(
    categoriesList: List<PlaceCategory>,
    onCategorySelected: (PlaceCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(categoriesList) { category ->
            PlaceCategoryCard(
                category = category,
                onClick = onCategorySelected,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun PlaceCategoryCard(
    category: PlaceCategory,
    onClick: (PlaceCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = { onClick(category) },
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = category.label,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(dimensionResource(R.dimen.card_padding)),
        )
    }
}

@Preview
@Composable
fun PlaceCategoryCardPreview() {
    PlaceCategoryCard(onClick = {}, category = PlaceCategory.PARKS)
}

@DevicesPreview
@Composable
fun MenuScreenPreviews() {
    LondonAppTheme {
        HomeScreen(
            onCategorySelected = {},
            onTabSelected = {},
        )
    }
}
