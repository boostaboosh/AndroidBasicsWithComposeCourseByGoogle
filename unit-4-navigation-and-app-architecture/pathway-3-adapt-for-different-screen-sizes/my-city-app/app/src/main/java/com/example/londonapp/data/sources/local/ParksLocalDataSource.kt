package com.example.londonapp.data.sources.local

import com.example.londonapp.R
import com.example.londonapp.data.sources.local.dataSourceModels.BookingRequirement
import com.example.londonapp.data.sources.local.dataSourceModels.Location
import com.example.londonapp.data.sources.local.dataSourceModels.Park
import com.example.londonapp.data.sources.local.dataSourceModels.price.SinglePrice

internal object ParksLocalDataSource {
    val parks = setOf(
        Park(
            name = "Hampstead Heath",
            pictureReferences = arrayOf(R.drawable.hampstead_heath),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/oUYaqr2YBaUBymEg8",
                cardinalCompassDirection = "North West",
                neighbourhoodName = "Hampstead Heath"
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = true,
            price = SinglePrice(0.0),
            description = "The heath is grassy and hilly, embracing ponds, swimming ponds, recent and ancient woodlands, a swimming lido, playgrounds, and a running track, and it adjoins the former stately home of Kenwood House and its estate. The south-east part of the heath is Parliament Hill, from which the view over London is protected by law. Although cycling unfriendly, you can usually cycle across it without being stopped.",
            hasParkrun = true,
            hasUnpavedTrails = true,
            isRecommendedForPicnic = true,
            isCyclingFriendly = true,
            areaInHectares = 320.0
        ),
        Park(
            name = "Richmond Park",
            pictureReferences = arrayOf(R.drawable.richmond_park),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/Sz7J2pLF7wijXCm27",
                cardinalCompassDirection = "South West",
                neighbourhoodName = "Richmond"
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = true,
            price = SinglePrice(0.0),
            description = "Expansive walled park, originally a 17th-century hunting ground and still home to hundreds of deer. Richmond Park, in the London Borough of Richmond upon Thames, is the largest of London's Royal Parks and is of national and international importance for wildlife conservation. It was created by Charles I in the 17th century as a deer park. Richmond Park includes many buildings of architectural or historic interest. The Grade I-listed White Lodge was formerly a royal residence and is now home to the Royal Ballet School. The park's boundary walls and ten other buildings are listed at Grade II, including Pembroke Lodge, the home of 19th-century British Prime Minister Lord John Russell and his grandson, the philosopher Bertrand Russell. Historically the preserve of the monarch, the park is now open for all to use and includes a golf course and other facilities for sport and recreation.",
            hasParkrun = true,
            hasUnpavedTrails = true,
            isRecommendedForPicnic = true,
            isCyclingFriendly = true,
            areaInHectares = 955.0
        ),
        Park(
            name = "Hyde Park",
            pictureReferences = arrayOf(R.drawable.hyde_park),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/ie62WN5r1dvxAcPZA",
                cardinalCompassDirection = "West",
                neighbourhoodName = "Hyde Park, below Paddington, west of Soho, above Kensington, east of Shepherd's Bush"
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = true,
            price = SinglePrice(0.0),
            description = "Hyde Park is a 350-acre (140 ha), historic Grade I-listed urban park in Westminster, Greater London. A Royal Park, it is the largest of the parks and green spaces that form a chain from Kensington Palace through Kensington Gardens and Hyde Park, via Hyde Park Corner and Green Park, past Buckingham Palace to St James's Park. Hyde Park is divided by the Serpentine and the Long Water lakes.\n"
                    + "\n"
                    + "The park was established by Henry VIII in 1536 when he took the land from Westminster Abbey and used it as a hunting ground. It opened to the public in 1637 and quickly became popular, particularly for May Day parades. Major improvements occurred in the early 18th century under the direction of Queen Caroline. The park also became a place for duels during this time, often involving members of the nobility. In the 19th century, The Great Exhibition of 1851 was held in the park, for which The Crystal Palace, designed by Joseph Paxton, was erected.\n"
                    + "\n"
                    + "Free speech and demonstrations have been a key feature of Hyde Park since the 19th century. Speakers' Corner has been established as a point of free speech and debate since 1872, while the Chartists, the Reform League, the suffragettes, and the Stop the War Coalition have all held protests there. In the late 20th century, the park was known for holding large-scale free rock music concerts, featuring groups such as Pink Floyd, The Rolling Stones and Queen. Major events in the park have continued into the 21st century, such as Live 8 in 2005, and the annual Hyde Park Winter Wonderland from 2007.",
            hasParkrun = false,
            hasUnpavedTrails = false,
            isRecommendedForPicnic = true,
            isCyclingFriendly = true,
            areaInHectares = 140.0
        ),
        Park(
            name = "The Regent's Park",
            pictureReferences = arrayOf(R.drawable.regents_park),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/zvGkcCAVtEssU6WH7",
                cardinalCompassDirection = "North West",
                neighbourhoodName = "West of Camden, above Hyde Park and Marylebone, east of Edgware Road, and below Belsize Park and Hampstead Heath"
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = true,
            price = SinglePrice(0.0),
            description = "Regent's Park (officially The Regent's Park) is one of the Royal Parks of London. It occupies 410 acres (170 ha) in north-west Inner London, administratively split between the City of Westminster and the Borough of Camden (and historically between Marylebone and Saint Pancras parishes)."
                    + "\n"
                    + "In addition to its large central parkland and ornamental lake, it contains various structures and organizations both public and private, generally on its periphery, including Regent's University and London Zoo."
                    + "\n"
                    + "What is now Regent's Park came into possession of the Crown upon the dissolution of the monasteries in the 1500s, and was used for hunting and tenant farming. In the 1810s, the Prince Regent proposed turning it into a pleasure garden. The park was designed by John Nash and James and Decimus Burton. Its construction was financed privately by James Burton after the Crown Estate rescinded its pledge to do so, and included development on the periphery of townhouses and expensive terrace dwellings. The park is Grade I listed on the Register of Historic Parks and Gardens."
                    + "\n"
                    + "As with listed buildings, parks and gardens are graded on a scale, Grade I being internationally significant sites are therefore the most important and constitute around 10% of the total number. Historically important gardens are Grade II* (about 30% of the total) and the remainder are of regional or national importance and are Grade II registered.",
            hasParkrun = false,
            hasUnpavedTrails = false,
            isRecommendedForPicnic = true,
            isCyclingFriendly = true,
            areaInHectares = 170.0
        ),
        Park(
            name = "Victoria Park",
            pictureReferences = arrayOf(R.drawable.victoria_park),
            location = Location(
                googleMapsLink = "https://maps.app.goo.gl/CQNQ6mx689B5BBS48",
                cardinalCompassDirection = "East",
                neighbourhoodName = "East of Hackney, North east of Bethnal Green, above Mile End, and west of Queen Elizabeth Olympic Park and south west of Hackney Marshes"
            ),
            bookingRequirement = BookingRequirement.UNNECESSARY,
            isRecommendedForKids = true,
            price = SinglePrice(0.0),
            description = "Victoria Park (known colloquially as Vicky Park or the People's Park) is a park in the London Borough of Tower Hamlets in East London, England."
                    + "\n"
                    + "There are two cafes in the park, The Pavilion Cafe in the west and The Hub in the east. There are two playgrounds, one on either side of the park, as well as sporting facilities and a skatepark in the east. The park is home to many historic artifacts and features and has decorative gardens and wilder natural areas as well as open grass lands. It also hosts a lawn bowls club.",
            hasParkrun = false,
            hasUnpavedTrails = false,
            isRecommendedForPicnic = true,
            isCyclingFriendly = true,
            areaInHectares = 86.18
        ),
    )
}
