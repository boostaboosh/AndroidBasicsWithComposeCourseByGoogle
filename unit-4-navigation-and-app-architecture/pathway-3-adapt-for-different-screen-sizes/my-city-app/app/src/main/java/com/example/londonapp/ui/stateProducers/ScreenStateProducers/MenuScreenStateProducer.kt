package com.example.londonapp.ui.stateProducers.ScreenStateProducers

import androidx.lifecycle.ViewModel
import com.example.londonapp.ui.stateProducers.userInterfaceStates.screenStates.MenuScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MenuScreenStateProducer : ViewModel() {
    // expose screen ui state
    private val _menuScreenState = MutableStateFlow(MenuScreenState())
    val menuScreenState: StateFlow<MenuScreenState> = _menuScreenState.asStateFlow()
}