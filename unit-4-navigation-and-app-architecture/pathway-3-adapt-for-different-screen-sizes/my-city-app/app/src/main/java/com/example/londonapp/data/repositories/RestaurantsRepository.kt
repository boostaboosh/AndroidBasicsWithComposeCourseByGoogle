package com.example.londonapp.data.repositories

import com.example.londonapp.data.sources.local.RestaurantsLocalDataSource
import com.example.londonapp.data.sources.local.dataSourceModels.Restaurant

class RestaurantsRepository(
    private val restaurantsLocalDataSource: RestaurantsLocalDataSource
) {
    fun fetchRestaurants() : List<Restaurant> { return restaurantsLocalDataSource.restaurants }
}
