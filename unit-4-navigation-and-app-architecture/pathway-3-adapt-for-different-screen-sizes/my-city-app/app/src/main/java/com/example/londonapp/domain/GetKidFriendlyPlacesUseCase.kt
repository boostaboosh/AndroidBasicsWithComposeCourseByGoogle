package com.example.londonapp.domain

import com.example.londonapp.data.repositories.KidFriendlyPlacesRepository
import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace

internal class GetKidFriendlyPlacesUseCase(
    private val kidFriendlyRepository: KidFriendlyPlacesRepository
) {
    operator fun invoke(): Set<RecommendedPlace> {
        return kidFriendlyRepository.fetchKidFriendlyPlaces()
    }
}
