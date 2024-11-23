package com.example.londonapp.data.sources.local.dataSourceModels

import com.example.londonapp.data.sources.local.dataSourceModels.price.Price

class Park(
    name: String,
    pictureReferences: Array<Int>,
    location: Location,
    bookingRequirement: BookingRequirement,
    isRecommendedForKids: Boolean = true,
    price: Price,
    description: String,
    val hasParkrun: Boolean,
    val hasUnpavedTrails: Boolean,
    val isRecommendedForPicnic: Boolean,
    val isCyclingFriendly: Boolean,
    val areaInHectares: Double,
) : RecommendedPlace(
    name = name,
    pictureReferences = pictureReferences,
    location = location,
    bookingRequirement = bookingRequirement,
    isRecommendedForKids = isRecommendedForKids,
    price = price,
    description = description
) {
}