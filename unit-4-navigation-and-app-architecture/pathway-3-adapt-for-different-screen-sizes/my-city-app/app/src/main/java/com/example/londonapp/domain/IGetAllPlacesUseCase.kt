package com.example.londonapp.domain

import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace

internal interface IGetAllPlacesUseCase{

    operator fun invoke() : Set<RecommendedPlace>

}

internal class GetAllPlacesUseCase(
    private val kidFriendlyPlacesUseCase: GetKidFriendlyPlacesUseCase,
    private val parksUseCase: GetParksUseCase,
    private val restaurantsUseCase: GetRestaurantsUseCase,
    private val shopsUseCase: GetShopsUseCase,
) : IGetAllPlacesUseCase {

    override fun invoke(): Set<RecommendedPlace> {
        return kidFriendlyPlacesUseCase.invoke() + parksUseCase.invoke() + restaurantsUseCase.invoke() + shopsUseCase.invoke()
    }

}
