package com.example.londonapp.ui.stateConsumers.components

import androidx.compose.runtime.Composable
import com.example.londonapp.ui.ScreenLayoutType

@Composable
fun AdaptiveLayout(
    layoutType: ScreenLayoutType,
    listOnlyLayout: @Composable () -> Unit,
    listAndDetailLayout: @Composable () -> Unit
) {
    if (layoutType == ScreenLayoutType.LIST_ONLY) { listOnlyLayout() }
    else if (layoutType == ScreenLayoutType.LIST_AND_DETAIL) { listAndDetailLayout() }
}