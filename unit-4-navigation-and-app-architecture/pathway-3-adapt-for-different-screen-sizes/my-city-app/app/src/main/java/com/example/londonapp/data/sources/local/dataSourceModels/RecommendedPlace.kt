package com.example.londonapp.data.sources.local.dataSourceModels

import com.example.londonapp.data.sources.local.dataSourceModels.price.Price

open class RecommendedPlace(
    val name: String,
    val pictureReferences: Array<Int>, // drawable resource IDs
    val location: Location,
    val bookingRequirement: BookingRequirement,
    val isRecommendedForKids: Boolean,
    val price: Price,
    val description: String
) {
    companion object {
        private var counter = 0

        private fun generateNextId(): Int {
            return ++counter
        }
    }
    val id: Int = generateNextId()
}
