package com.example.londonapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.londonapp.ui.navigation.ScreenNavigator
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
                ScreenNavigator()
            }
        }
    }
}
