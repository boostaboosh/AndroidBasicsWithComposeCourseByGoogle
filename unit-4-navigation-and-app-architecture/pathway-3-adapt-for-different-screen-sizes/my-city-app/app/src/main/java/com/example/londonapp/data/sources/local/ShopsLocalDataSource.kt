package com.example.londonapp.data.sources.local

import com.example.londonapp.data.sources.local.dataSourceModels.BookingRequirement
import com.example.londonapp.data.sources.local.dataSourceModels.Location
import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace
import com.example.londonapp.data.sources.local.dataSourceModels.price.PriceRange
import com.example.londonapp.data.sources.local.dataSourceModels.price.SinglePrice

class ShopsLocalDataSource {
    val shops = listOf(
        RecommendedPlace(
            name = "John Lewis Flagship UK department store",
            pictureReferences = arrayOf(),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/aMurMNADgE33AX1z5",
                cardinalCompassDirection = "Centre West",
                neighbourhoodName = "Oxford Street, between Marylebone, Mayfair, and Soho",
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = false,
            price = PriceRange(minimumPrice = SinglePrice(50.0), maximumPrice = SinglePrice(10000.0)),
            description = "Flagship of respected UK department store specialising in homewares and branded goods of various types.",
        ),
        RecommendedPlace(
            name = "Conservatory Archives Middleton Mews plant shop",
            pictureReferences = arrayOf(),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/9Wxb4dVcgNYcF3C79",
                cardinalCompassDirection = "North",
                neighbourhoodName = "Islington",
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = false,
            price = PriceRange(minimumPrice = SinglePrice(10.0), maximumPrice = SinglePrice(10000.0)),
            description = "This location has both outdoor and indoor plants, including some impressive cacti specimens as well as some other rare and unusual species. It also carries the usual houseplant species (monstera, ferns, fiddle leafs etc) as well as pots and other handy tools. The staff are helpful and knowledgeable. "
                    + "\n"
                    + "This place has a beautiful selection of plants and garden goods, with an especially great selection of houseplants. Houseplants are extremely reasonably priced and they have some charming tiny ones that are great as a gift.",
        ),
        RecommendedPlace(
            name = "TFC Turkish Supermarket, Dalston",
            pictureReferences = arrayOf(),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/2b9ygBPfs2QZeuHy7",
                cardinalCompassDirection = "North East",
                neighbourhoodName = "Dalston",
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = false,
            price = PriceRange(minimumPrice = SinglePrice(5.0), maximumPrice = SinglePrice(10000.0)),
            description = "A turkish supermarket chain known for its amazing bread. Also features turkish pastries, olives, and mediterranean goods.",
        ),
        RecommendedPlace(
            name = "Salvino",
            pictureReferences = arrayOf(),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/81efbQgLfyCGzvZy8",
                cardinalCompassDirection = "North",
                neighbourhoodName = "East of Kentish Town and South East of Tufnell Park",
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = false,
            price = PriceRange(minimumPrice = SinglePrice(10.0), maximumPrice = SinglePrice(10000.0)),
            description = "Italian delicatessen shop featuring italian food products",
        ),
        RecommendedPlace(
            name = "N1 Garden Centre",
            pictureReferences = arrayOf(),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/3eySuvi9yLy28B9S6",
                cardinalCompassDirection = "North East",
                neighbourhoodName = "Dalston",
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = false,
            price = PriceRange(minimumPrice = SinglePrice(10.0), maximumPrice = SinglePrice(10000.0)),
            description = "British-grown indoor and outdoor plants in small site also selling patio furniture and garden pots.",
        ),
        RecommendedPlace(
            name = "Fara Charity Shop",
            pictureReferences = arrayOf(),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/cCV6fnSQGBjPEwvG9",
                cardinalCompassDirection = "North",
                neighbourhoodName = "Islington",
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = false,
            price = PriceRange(minimumPrice = SinglePrice(5.0), maximumPrice = SinglePrice(10000.0)),
            description = "Charity retailer selling donated secondhand goods to benefit Romanian families & children in need."
                    + "\n"
                    + "A charity shop with a huge range of clothing at charity shop prices."
                    + "Majority of clothing around or under £5 with many items marked down to £1 and £2.",
        ),
    )
}
