package com.example.londonapp.data.sources.local.dataSourceModels.price

class PriceRange(
    val minimumPrice: SinglePrice,
    val maximumPrice: SinglePrice,
): Price() {
    init {
        if (!(minimumPrice.compareTo(maximumPrice) < 0)) throw IllegalArgumentException("min price must be < max price")
    }

    override fun getAffordabilityLevel(): AffordabilityLevel {
        return minimumPrice.getAffordabilityLevel()
    }

}