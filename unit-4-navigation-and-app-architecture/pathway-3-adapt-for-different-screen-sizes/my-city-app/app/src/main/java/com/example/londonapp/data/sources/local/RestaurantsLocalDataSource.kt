package com.example.londonapp.data.sources.local

import com.example.londonapp.R
import com.example.londonapp.data.sources.local.dataSourceModels.BookingRequirement
import com.example.londonapp.data.sources.local.dataSourceModels.Location
import com.example.londonapp.data.sources.local.dataSourceModels.Restaurant
import com.example.londonapp.data.sources.local.dataSourceModels.price.PriceRange
import com.example.londonapp.data.sources.local.dataSourceModels.price.SinglePrice

class RestaurantsLocalDataSource {
    val restaurants = listOf(
        Restaurant(
            name = "Halepi",
            pictureReferences = arrayOf(R.drawable.halepi),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/n6Bqs6hpZzBtyawJ8",
                cardinalCompassDirection = "West",
                neighbourhoodName = "Paddington, NW of Hyde Park"
            ),
            bookingRequirement = BookingRequirement.RECOMMENDED,
            isRecommendedForKids = false,
            pricePerPerson = PriceRange(SinglePrice(40.0), SinglePrice(80.0)),
            description = "Greek specialities served in cosy, family-run taverna decorated with handmade traditional fabrics.",
            takesCard = true,
            cuisine = "Greek",
            recommendedByWordOfMouth = true,
        ),
        Restaurant(
            name = "Bona",
            pictureReferences = arrayOf(R.drawable.bona),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/j4xHRXUKyThudNhV6",
                cardinalCompassDirection = "South",
                neighbourhoodName = "Forest Hill, Dulwich"
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = false,
            pricePerPerson = PriceRange(SinglePrice(15.0), SinglePrice(30.0)),
            description = "Hip venue for wood-fired Neapolitan-style pizzas on sourdough crust served with beer & wine. Recommended by neapolitan pizza makers from Italy.",
            takesCard = true,
            cuisine = "Pizza",
            recommendedByWordOfMouth = true,
        ),
        Restaurant(
            name = "The French House",
            pictureReferences = arrayOf(R.drawable.french_house_front, R.drawable.french_house_inside, R.drawable.french_house_bar),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/6AjG3cMPAKjjejf19",
                cardinalCompassDirection = "Centre West",
                neighbourhoodName = "Leicester Square, above chinatown, and near Soho"
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = false,
            pricePerPerson = PriceRange(SinglePrice(40.0), SinglePrice(70.0)),
            description = "Compact bar hung with photos, where literary crowd prefer wine to beer and embrace the no-tech rule.",
            takesCard = true,
            cuisine = "French",
            recommendedByWordOfMouth = true,
        ),
        Restaurant(
            name = "Le Beaujolais",
            pictureReferences = arrayOf(R.drawable.le_beaujolais),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/aouaYi97fWJkgTGY9",
                cardinalCompassDirection = "Centre West",
                neighbourhoodName = "Leicester Square, above chinatown, and near Soho"
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = false,
            pricePerPerson = PriceRange(SinglePrice(40.0), SinglePrice(70.0)),
            description = "Ties and tankards hang off the ceiling in this intimate, lively wine bar serving rustic French food.",
            takesCard = true,
            cuisine = "French",
            recommendedByWordOfMouth = true,
        ),
        Restaurant(
            name = "Calabar Zone",
            pictureReferences = arrayOf(R.drawable.calabar_zone),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/4XRy6RADwixcRGC19",
                cardinalCompassDirection = "South East",
                neighbourhoodName = "North-east of Burgess Park, between Walworth and Bermondsey"
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = false,
            pricePerPerson = PriceRange(SinglePrice(25.0), SinglePrice(45.0)),
            description = "West african diner style restaurant. Serves Jollof rice which is a rice dish the nigerian nurse at Charring Cross A&E recommended trying.",
            takesCard = true,
            cuisine = "Nigerian, West African",
            recommendedByWordOfMouth = false,
        ),
        Restaurant(
            name = "Afghan Kitchen",
            pictureReferences = arrayOf(R.drawable.afghan_kitchen),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/Gdt4y35SBg1TsgUq6",
                cardinalCompassDirection = "North Central",
                neighbourhoodName = "Between Angel and Islington"
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = false,
            pricePerPerson = PriceRange(SinglePrice(15.0), SinglePrice(25.0)),
            description = "Compact dining room with shared tables and a menu of homemade bread, warming stews and rice dishes.",
            takesCard = false,
            cuisine = "Afghani",
            recommendedByWordOfMouth = true,
        ),
    )
}
