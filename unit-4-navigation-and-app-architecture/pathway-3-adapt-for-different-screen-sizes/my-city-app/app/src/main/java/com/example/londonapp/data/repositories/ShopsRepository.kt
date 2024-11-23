package com.example.londonapp.data.repositories

import com.example.londonapp.data.sources.local.ShopsLocalDataSource
import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace

class ShopsRepository(
    private val shopsLocalDataSource: ShopsLocalDataSource
) {
    fun fetchShops(): List<RecommendedPlace> { return shopsLocalDataSource.shops }
}