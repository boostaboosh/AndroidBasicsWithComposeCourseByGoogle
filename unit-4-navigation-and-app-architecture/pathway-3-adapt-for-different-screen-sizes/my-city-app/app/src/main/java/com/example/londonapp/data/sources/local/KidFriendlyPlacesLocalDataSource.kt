package com.example.londonapp.data.sources.local

import com.example.londonapp.data.sources.local.dataSourceModels.BookingRequirement
import com.example.londonapp.data.sources.local.dataSourceModels.Location
import com.example.londonapp.data.sources.local.dataSourceModels.RecommendedPlace
import com.example.londonapp.data.sources.local.dataSourceModels.price.SinglePrice

class KidFriendlyPlacesLocalDataSource {
    val kidFriendlyPlaces = listOf(
        RecommendedPlace(
            name = "The Science Museum",
            pictureReferences = arrayOf(),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/TCVWq5mSHEFYws2z8",
                cardinalCompassDirection = "Centre",
                neighbourhoodName = "South Kensingtion, below Hyde Park"
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = true,
            price = SinglePrice(0.0),
            description = "A vast treasury of science & invention, famous for its state-of-the-art interactive exhibits."
        ),
        RecommendedPlace(
            name = "The Natural History Museum",
            pictureReferences = arrayOf(),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/W62mGuFf5C1g9Dd59",
                cardinalCompassDirection = "Centre",
                neighbourhoodName = "South Kensingtion, below Hyde Park"
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = true,
            price = SinglePrice(0.0),
            description = "Landmark museum of animals and natural phenomena, with hands-on exhibits and animatronic dinosaurs."
        ),
        RecommendedPlace(
            name = "Hampstead Heath Mixed Bathing Pond",
            pictureReferences = arrayOf(),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/9uQupUGQjQ3sruZH9",
                cardinalCompassDirection = "North West",
                neighbourhoodName = "Hampstead Heath"
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = true,
            price = SinglePrice(5.0),
            description = "The majority of the ponds on Hampstead Heath are fed by the headwater springs of the River Fleet. Three of the main ponds are now large freshwater bathing/swimming ponds: two designated single sex (Highgate no. 2 male and Highgate no. 5 female); and one for mixed bathing (Hampstead no. 3). The bathing ponds are not the only special-use ponds, however: Highgate no. 3 pond is the Model Boating Pond and it, along with a few other ponds, are open to anglers. A number of the other ponds are set aside as wildlife reserves or are purely ornamental (such as the more minor Viaduct Pond). The City of London Corporation tried to close the bathing ponds in 2004, but a challenge at the High Court by swimmers overcame this, though charges for swimming were introduced."
        ),
        RecommendedPlace(
            name = "London Aquarium Sea Life",
            pictureReferences = arrayOf(),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/ZU6NKufDaB5C8cgX6",
                cardinalCompassDirection = "Centre",
                neighbourhoodName = "South Bank of river Thames near Waterloo station and opposite of Big Ben near Westminster Bridge"
            ),
            bookingRequirement = BookingRequirement.REQUIRED,
            isRecommendedForKids = true,
            price = SinglePrice(27.50),
            description = "Family-friendly exhibits with a variety of sea creatures."
        ),
        RecommendedPlace(
            name = "London Zoo",
            pictureReferences = arrayOf(),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/txWWuJG4A9Qb4Ymi6",
                cardinalCompassDirection = "North West",
                neighbourhoodName = "North side of Regent's park, west of Camden Town"
            ),
            bookingRequirement = BookingRequirement.REQUIRED,
            isRecommendedForKids = true,
            price = SinglePrice(31.0),
            description = "Major city zoo and research centre helping to conserve numerous species in naturalistic habitats."
        ),
    )
}