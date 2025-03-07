package com.example.londonapp.domain

import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace

class GetPlacesByCategoryUseCase(
    private val getParksUseCase: GetParksUseCase,
    private val getShopsUseCase: GetShopsUseCase,
    private val getKidFriendlyPlacesUseCase: GetKidFriendlyPlacesUseCase,
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
) {
    operator fun invoke(placeCategory: PlaceCategory): Set<RecommendedPlace> {
        return when (placeCategory) {
            PlaceCategory.PARKS -> getParksUseCase()
            PlaceCategory.RESTAURANTS -> getRestaurantsUseCase()
            PlaceCategory.KID_FRIENDLY -> getKidFriendlyPlacesUseCase()
            PlaceCategory.SHOPPING -> getShopsUseCase()
        }
    }
}
