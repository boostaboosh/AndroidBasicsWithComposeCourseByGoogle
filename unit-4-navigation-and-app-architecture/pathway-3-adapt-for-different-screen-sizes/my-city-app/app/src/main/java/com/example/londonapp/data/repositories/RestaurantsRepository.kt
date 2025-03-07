package com.example.londonapp.data.repositories

import com.example.londonapp.data.sources.local.RestaurantsLocalDataSource
import com.example.londonapp.data.sources.local.dataSourceModels.Restaurant

internal class RestaurantsRepository(
    private val restaurantsLocalDataSource: RestaurantsLocalDataSource
) {
    fun fetchRestaurants() : Set<Restaurant> { return restaurantsLocalDataSource.restaurants }
}
