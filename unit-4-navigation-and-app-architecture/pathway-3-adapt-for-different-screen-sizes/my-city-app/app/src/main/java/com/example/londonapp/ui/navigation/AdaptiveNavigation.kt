package com.example.londonapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.londonapp.R
import com.example.londonapp.domain.PlaceCategory

@Composable
fun AdaptiveNavigation(
    selectedTab: Destination,
    onTabSelected: (Destination) -> Unit,
    content: @Composable () -> Unit,
) {
    val windowWidth = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    when (windowWidth) {
        WindowWidthSizeClass.EXPANDED -> {
            Row {
                NavDrawer(selectedTab, onTabSelected, content)
            }
        }
        WindowWidthSizeClass.MEDIUM -> {
            Row {
                NavRail(selectedTab, onTabSelected)
                content()
            }
        }
        // WindowWidthSizeClass.COMPACT
        else -> {
            Column {
                Column(Modifier.weight(1f)) { content() }
                BottomNavBar(selectedTab, onTabSelected)
            }
        }
    }
}

@Composable
fun NavDrawer(
    selectedTab: Destination,
    onTabSelected: (Destination) -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    PermanentNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            PermanentDrawerSheet(
                modifier = Modifier.width(dimensionResource(R.dimen.drawer_width))
            ) {
                NavDrawerContent(
                    selectedTab = selectedTab,
                    onTabSelected = onTabSelected,
                )
            }
        }
    ) {
        VerticalDivider(modifier = modifier.fillMaxHeight(), thickness = 1.dp, color = Color.Black)
        content()
    }
}

@Preview
@Composable
fun NavDrawerPreview() {
    NavDrawer(
        selectedTab = Home(),
        onTabSelected = {},
        content = {}
    )
}

@Composable
fun NavDrawerContent(
    selectedTab: Destination,
    onTabSelected: (Destination) -> Unit,
) {
    Column {
        NavDrawerHeader(modifier = Modifier.fillMaxWidth())
        for (navItem in navItemList) {
            NavigationDrawerItem(
                label = {
                    Text(
                        text = stringResource(navItem.text),
                    )
                },
                selected = navItem.route == selectedTab,
                onClick = { onTabSelected(navItem.route) },
                icon = { NavIcon(navItem) },
            )
        }
    }
}

@Composable
fun NavDrawerHeader(
    modifier: Modifier = Modifier,
) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(R.dimen.nav_drawer_header_vertical_padding)
                ),
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Composable
fun NavRail(
    selectedTab: Destination,
    onTabSelected: (Destination) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row {
        NavigationRail(
            modifier = modifier,
        ) {
            for (navItem in navItemList) {
                NavigationRailItem(
                    selected = navItem.route == selectedTab,
                    onClick = { onTabSelected(navItem.route) },
                    icon = { NavIcon(navItem) },
                )
            }
        }
        VerticalDivider(modifier = modifier.fillMaxHeight(), thickness = 1.dp, color = Color.Black)
    }
}

@Preview
@Composable
fun NavRailPreview() {
    NavRail(
        selectedTab = Home(),
        onTabSelected = {},
    )
}

@Composable
fun BottomNavBar(
    selectedTab: Destination,
    onTabSelected: (Destination) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        HorizontalDivider(modifier = modifier.fillMaxWidth(), thickness = 1.dp, color = Color.Black)
        NavigationBar(
            modifier = modifier,
        ) {
            for (navItem in navItemList) {
                NavigationBarItem(
                    selected = navItem.route == selectedTab,
                    onClick = { onTabSelected(navItem.route) },
                    icon = { NavIcon(navItem) },
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomNavBarPreview() {
    BottomNavBar(
        selectedTab = Home(),
        onTabSelected = {},
    )
}

@Composable
fun NavIcon(
    navItem: NavItem,
) {
    Icon(
        painter = painterResource(navItem.icon),
        contentDescription = stringResource(navItem.text),
    )
}

private val navItemList: List<NavItem> = listOf(
    NavItem(
        route = Home(),
        icon = R.drawable.baseline_home_24,
        text = R.string.home,
    ),
    NavItem(
        route = RecommendedPlacesList(category = PlaceCategory.PARKS),
        icon = R.drawable.baseline_park_24,
        text = R.string.parks,
    ),
    NavItem(
        route = RecommendedPlacesList(category = PlaceCategory.RESTAURANTS),
        icon = R.drawable.baseline_restaurant_24,
        text = R.string.restaurants,
    ),
    NavItem(
        route = RecommendedPlacesList(category = PlaceCategory.KID_FRIENDLY),
        icon = R.drawable.baseline_child_care_24,
        text = R.string.kid_friendly,
    ),
    NavItem(
        route = RecommendedPlacesList(category = PlaceCategory.SHOPPING),
        icon = R.drawable.baseline_shopping_bag_24,
        text = R.string.shopping,
    )
)

class NavItem(
    val route: Destination,
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
)
