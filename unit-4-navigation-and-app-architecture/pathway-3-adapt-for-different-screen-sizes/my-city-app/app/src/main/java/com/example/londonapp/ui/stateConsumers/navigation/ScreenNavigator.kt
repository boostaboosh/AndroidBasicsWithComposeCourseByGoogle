package com.example.londonapp.ui.stateConsumers.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.londonapp.ui.ScreenLayoutType
import com.example.londonapp.ui.ScreenNavigationBarType
import com.example.londonapp.ui.stateConsumers.Screens
import com.example.londonapp.ui.stateConsumers.screens.MenuScreen
import com.example.londonapp.ui.stateConsumers.screens.PlaceDetailsScreen
import com.example.londonapp.ui.stateConsumers.screens.PlacesListScreen
import com.example.londonapp.ui.stateProducers.NavigationStateProducer

@Composable
fun AdaptiveLayoutAndNavigation(
    layoutType: ScreenLayoutType,
    navigationBarType: ScreenNavigationBarType,
    navigationStateProducer: NavigationStateProducer = viewModel(),
) {
    val navigationController = rememberNavController()
    when (layoutType) {
        ScreenLayoutType.LIST_ONLY -> ListOnlyNavigation(
            navigationController = navigationController,
            navigationBarType = navigationBarType,
        )
        ScreenLayoutType.LIST_AND_DETAIL -> ListAndDetailNavigation(
            navigationController = navigationController,
            navigationBarType = navigationBarType,
        )
    }
}

@Composable
fun ListOnlyNavigation(
    navigationController: NavHostController,
    navigationBarType: ScreenNavigationBarType,
) {
    NavHost(
        navController = navigationController,
        startDestination = Screens.MenuScreen,
        modifier = Modifier,
    ) {
        val layoutType = ScreenLayoutType.LIST_ONLY
        composable(route = Screens.MenuScreen.name) {
            MenuScreen(
                layoutType = layoutType,
                navigationBarType = navigationBarType,
                menuScreenStateProducer = viewModel(),
                onListItemPressed = { /* todo */ },
            )
        }
        composable(route = Screens.PlacesListScreen.name) {
            PlacesListScreen(
                layoutType = layoutType,
                navigationBarType = navigationBarType,
                onListItemPressed = { /*todo*/ },
                onBackPressed = { /*todo*/ },
            )
        }
        composable(route = Screens.PlaceDetailsScreen.name) {
            PlaceDetailsScreen(
                layoutType = layoutType,
                navigationBarType = navigationBarType,
                onMapsLinkPressed = {/*todo*/},
                onBackPressed = { /* todo (only on compact and medium windows widths) */ },
            )
        }
    }
}

@Composable
fun ListAndDetailNavigation(
    navigationController: NavHostController,
    navigationBarType: ScreenNavigationBarType,
) {
    NavHost(
        navController = navigationController,
        startDestination = ListAndDetailScreen.MenuAndPlacesScreen,
        modifier = Modifier,
    ) {
        val layoutType = ScreenLayoutType.LIST_AND_DETAIL
        composable(route = Screens.MenuScreen.name) {
            MenuAndPlacesListScreen(
                layoutType = layoutType,
                navigationBarType = navigationBarType,
                menuScreenStateProducer = viewModel(),
                onListItemPressed = { /* todo */ },
            )
        }
        composable(route = Screens.PlacesListScreen.name) {
            PlacesListAndPlacesDetailsScreen(
                layoutType = layoutType,
                navigationBarType = navigationBarType,
                onListItemPressed = { /*todo*/ },
                onBackPressed = { /*todo*/ },
            )
        }
    }
}