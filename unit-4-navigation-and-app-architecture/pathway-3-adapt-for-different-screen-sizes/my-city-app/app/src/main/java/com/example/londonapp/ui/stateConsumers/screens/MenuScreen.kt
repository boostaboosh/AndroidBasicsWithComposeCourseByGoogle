package com.example.londonapp.ui.stateConsumers.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.londonapp.R
import com.example.londonapp.ui.navigation.AdaptiveNavigation
import com.example.londonapp.ui.navigation.Destination
import com.example.londonapp.ui.navigation.Home
import com.example.londonapp.domain.PlaceCategory
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
        modifier = modifier.padding(horizontal = 12.dp).fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.Bottom),
        reverseLayout = true,
    ) {
        item { Spacer(modifier.size(0.dp)) }
        items(categoriesList) { category ->
            PlaceCategoryCard(
                text = category.label,
                onClick = { onCategorySelected(category) },
            )
        }
        item { Spacer(modifier.size(0.dp)) }
    }
}

@Composable
fun PlaceCategoryCard(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        border = BorderStroke(
            width = 1.dp,
            Color(red = 0x00, green = 0x00, blue = 0x00, alpha = 0xFF)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp,
        ),
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = text,
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
    PlaceCategoryCard(text = "parks", onClick = {})
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
