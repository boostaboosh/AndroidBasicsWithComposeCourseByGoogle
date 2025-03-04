package com.example.londonapp.domain

import com.example.londonapp.data.repositories.RestaurantsRepository
import com.example.londonapp.data.sources.local.dataSourceModels.Restaurant

class GetRestaurantsUseCase(
    private val restaurantsRepository: RestaurantsRepository
) {
    operator fun invoke(): List<Restaurant> { return restaurantsRepository.fetchRestaurants() }
}
