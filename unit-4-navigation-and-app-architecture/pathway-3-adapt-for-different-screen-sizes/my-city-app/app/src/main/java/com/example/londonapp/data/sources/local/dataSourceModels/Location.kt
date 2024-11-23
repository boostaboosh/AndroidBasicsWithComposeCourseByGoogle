package com.example.londonapp.data.sources.local.dataSourceModels

data class Location (
    val googleMapsLink: String, // e.g. google.com/place/sd23rXwe234df
    val cardinalCompassDirection: String, // e.g. NW
    val neighbourhoodName: String // e.g. Westminster
)