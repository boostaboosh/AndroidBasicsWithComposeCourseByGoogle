package com.example.londonapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.londonapp.ui.stateConsumers.screens.HomeScreen
import com.example.londonapp.ui.stateConsumers.screens.PlaceDetailsScreen
import com.example.londonapp.ui.stateConsumers.screens.PlacesListScreen

@Composable
fun ScreenNavigator(navigationController: NavHostController = rememberNavController()) {
//    val backStackTop by navigationController.currentBackStackEntryAsState()
//    val currentScreen = Screens.valueOf(
//        backStackTop?.destination?.route ?: Screens.MenuScreen.name
//    )

    val onTabSelected: (Destination) -> Unit = { route ->
        if (navigationController.currentDestination?.route != route::class.simpleName) {
            navigationController.navigate(route)
        }
    }

    NavHost(
        navController = navigationController,
        startDestination = Home(),
    ) {
        composable<Home> {
            HomeScreen(
                onTabSelected = onTabSelected,
                onCategorySelected = { category ->
                    navigationController.navigate(RecommendedPlacesList(category))
                },
            )
        }
        composable<RecommendedPlacesList> { backStackEntry ->
            val recommendedPlacesListArguments: RecommendedPlacesList = backStackEntry.toRoute()
            PlacesListScreen(
                placeCategory = recommendedPlacesListArguments.category,
                onListItemPressed = { placeId -> navigationController.navigate(PlaceDetails(placeId = placeId)) },
                onTabSelected = onTabSelected,
                onBackPressed = { navigationController.popBackStack() },
            )
        }
        composable<PlaceDetails> { backStackEntry ->
            val placeDetailsArguments: PlaceDetails = backStackEntry.toRoute()
            PlaceDetailsScreen(
                placeId = placeDetailsArguments.placeId,
                onBackPressed = { navigationController.popBackStack() },
            )
        }
    }
}
