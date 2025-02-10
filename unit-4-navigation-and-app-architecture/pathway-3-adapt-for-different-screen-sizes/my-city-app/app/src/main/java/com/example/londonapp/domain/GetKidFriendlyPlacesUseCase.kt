package com.example.londonapp.domain

import com.example.londonapp.data.repositories.KidFriendlyPlacesRepository
import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace

class GetKidFriendlyPlacesUseCase(
    val kidFriendlyRepository: KidFriendlyPlacesRepository
) {
    operator fun invoke(): List<RecommendedPlace> {
        return kidFriendlyRepository.fetchKidFriendlyPlaces()
    }
}