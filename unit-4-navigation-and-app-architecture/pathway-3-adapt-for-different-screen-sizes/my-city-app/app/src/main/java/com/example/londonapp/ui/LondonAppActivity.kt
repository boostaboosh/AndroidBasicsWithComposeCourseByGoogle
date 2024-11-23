package com.example.londonapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.londonapp.ui.stateConsumers.navigation.AdaptiveLayoutAndNavigation
import com.example.londonapp.ui.stateConsumers.theme.LondonAppTheme

class LondonAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // todo: call setContent to set activity content to a composable
        //  call composable or nav host and pass it the windowsize class
        //  from the currentWindowAdaptiveInfo() top level function
        //  of the compose material 3 adaptive library
        setContent{
            LondonAppTheme{
                val windowWidth = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

                val layoutType: ScreenLayoutType
                val navigationType: ScreenNavigationBarType
                when (windowWidth) {
                    WindowWidthSizeClass.COMPACT -> {
                        layoutType = ScreenLayoutType.LIST_ONLY
                        navigationType = ScreenNavigationBarType.BOTTOM_NAVIGATION
                    }
                    WindowWidthSizeClass.MEDIUM -> {
                        layoutType = ScreenLayoutType.LIST_ONLY
                        navigationType = ScreenNavigationBarType.NAVIGATION_RAIL
                    }
                    WindowWidthSizeClass.EXPANDED -> {
                        layoutType = ScreenLayoutType.LIST_AND_DETAIL
                        navigationType = ScreenNavigationBarType.NAVIGATION_DRAWER
                    }
                    else -> {
                        layoutType = ScreenLayoutType.LIST_ONLY
                        navigationType = ScreenNavigationBarType.BOTTOM_NAVIGATION
                    }
                }

                AdaptiveLayoutAndNavigation(
                    layoutType = layoutType,
                    navigationBarType = navigationType
                )
            }
        }
    }
}

enum class ScreenLayoutType{
    LIST_ONLY,
    LIST_AND_DETAIL,
}

enum class ScreenNavigationBarType{
    BOTTOM_NAVIGATION,
    NAVIGATION_RAIL,
    NAVIGATION_DRAWER
}