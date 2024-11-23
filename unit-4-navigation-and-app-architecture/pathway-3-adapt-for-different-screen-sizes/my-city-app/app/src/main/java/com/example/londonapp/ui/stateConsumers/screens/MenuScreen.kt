package com.example.londonapp.ui.stateConsumers.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.londonapp.ui.ScreenLayoutType
import com.example.londonapp.ui.ScreenNavigationBarType
import com.example.londonapp.ui.stateConsumers.components.AdaptiveLayout
import com.example.londonapp.ui.stateConsumers.theme.LondonAppTheme
import com.example.londonapp.ui.stateProducers.ScreenStateProducers.MenuScreenStateProducer

@Composable
fun MenuScreen(
    layoutType: ScreenLayoutType,
    navigationBarType: ScreenNavigationBarType,
    menuScreenStateProducer: MenuScreenStateProducer = viewModel(),
    onListItemPressed: (/*place reference*/) -> Unit
) {
    val state = menuScreenStateProducer.menuScreenState.collectAsState().value
    AdaptiveLayout(
        layoutType = layoutType,
        listOnlyLayout = ListOnlyLayout(),
        listAndDetailLayout = ListAndDetailLayout(),
    )
}

@Preview(device = Devices.PHONE, showBackground = true)
@Composable
fun MenuScreenCompactPreview() {
    LondonAppTheme {
        MenuScreen(
            layoutType = ScreenLayoutType.LIST_ONLY,
            navigationType = ScreenNavigationBarType.BOTTOM_NAVIGATION,
            onListItemPressed = {}
        )
    }
}

@Preview(device = Devices.TABLET, showBackground = true)
@Composable
fun MenuScreenMediumPreview() {
    LondonAppTheme {
        MenuScreen(
            layoutType = ScreenLayoutType.LIST_ONLY,
            navigationType = ScreenNavigationBarType.NAVIGATION_RAIL,
            onListItemPressed = {}
        )
    }
}

@Preview(device = Devices.DESKTOP, showBackground = true)
@Composable
fun MenuScreenExtendedPreview() {
    LondonAppTheme {
        MenuScreen(
            layoutType = ScreenLayoutType.LIST_AND_DETAIL,
            navigationType = ScreenNavigationBarType.NAVIGATION_DRAWER,
            onListItemPressed = {}
        )
    }
}
