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
        if (navigationController.currentDestination?.route != route::class.simpleName) { // todo: not sure if this works as expected
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
                onTabSelected = onTabSelected,
                onListItemPressed = { placeId -> /*todo: navigate to places details screen (only on compact and medium window widths)*/
                    navigationController.navigate(PlaceDetails(placeId = placeId))
                },
                onBackPressed = { /*todo: navigate to menu screen, is this automatic? --> only 1 way to find out: TRY IT!!*/ },
            )
        }
        composable<PlaceDetails> { backStackEntry ->
            val placeDetails: PlaceDetails = backStackEntry.toRoute()
            PlaceDetailsScreen(
                placeId = placeDetails.placeId,
                onTabSelected = onTabSelected,
                onBackPressed = { /*todo: navigate to places list screen (only on compact and medium windows widths), is this automatic?*/ },
            )
        }
    }
}