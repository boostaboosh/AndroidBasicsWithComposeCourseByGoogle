package com.example.londonapp.data.sources.local.dataSourceModels

import com.example.londonapp.data.sources.local.dataSourceModels.price.Price
import com.example.londonapp.data.sources.local.dataSourceModels.price.PriceRange

class Restaurant(
    name: String,
    pictureReferences: Array<Int>,
    location: Location,
    bookingRequirement: BookingRequirement,
    isRecommendedForKids: Boolean,
    pricePerPerson: Price,
    description: String,
    val takesCard: Boolean,
    val cuisine: String,
    val recommendedByWordOfMouth: Boolean,
    ) : RecommendedPlace(
        name = name,
        pictureReferences = pictureReferences,
        location = location,
        bookingRequirement = bookingRequirement,
        isRecommendedForKids = isRecommendedForKids,
        price = pricePerPerson,
        description = description
    )
