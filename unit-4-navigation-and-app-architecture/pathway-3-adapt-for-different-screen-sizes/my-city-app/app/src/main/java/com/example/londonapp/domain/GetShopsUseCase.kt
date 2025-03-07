package com.example.londonapp.domain

import com.example.londonapp.data.repositories.ShopsRepository
import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace

class GetShopsUseCase(
    private val shopsRepository: ShopsRepository
) {
    operator fun invoke(): Set<RecommendedPlace> { return shopsRepository.fetchShops() }
}
