package com.example.londonapp.domain

import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace

internal interface IGetPlaceByIdUseCase {

    operator fun invoke(placeId: Int): RecommendedPlace

}

internal class GetPlaceByIdUseCase(
    private val getAllPlacesUseCase: IGetAllPlacesUseCase,
) : IGetPlaceByIdUseCase {

    override fun invoke(placeId: Int): RecommendedPlace {
        val allPlaces = getAllPlacesUseCase()
        return allPlaces.firstOrNull { it.id == placeId }
            ?: throw PlaceNotFoundException("Place with id $placeId not found.")
    }

}

internal class PlaceNotFoundException(message: String) : Exception(message)
