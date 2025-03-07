package com.example.londonapp.data.repositories

import com.example.londonapp.data.sources.local.KidFriendlyPlacesLocalDataSource
import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace

internal class KidFriendlyPlacesRepository(
    private val kidFriendlyPlacesLocalDataSource: KidFriendlyPlacesLocalDataSource
) {
    fun fetchKidFriendlyPlaces(): Set<RecommendedPlace> { return kidFriendlyPlacesLocalDataSource.kidFriendlyPlaces }
}
