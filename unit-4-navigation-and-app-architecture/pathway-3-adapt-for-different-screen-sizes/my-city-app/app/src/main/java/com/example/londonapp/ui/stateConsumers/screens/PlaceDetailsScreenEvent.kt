package com.example.londonapp.ui.stateConsumers.screens

internal sealed class PlaceDetailsScreenEvent {

    data class OpenMap(val mapsLink: String): PlaceDetailsScreenEvent()

}
