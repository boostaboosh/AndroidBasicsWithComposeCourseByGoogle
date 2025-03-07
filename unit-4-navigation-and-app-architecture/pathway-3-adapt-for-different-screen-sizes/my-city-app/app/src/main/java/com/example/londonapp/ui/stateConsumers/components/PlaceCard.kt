package com.example.londonapp.ui.stateConsumers.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.londonapp.R
import com.example.londonapp.ui.stateConsumers.theme.LondonAppTheme
import com.example.londonapp.ui.stateConsumers.theme.Typography

@Composable
fun PlaceCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @DrawableRes imageRes: Int?,
    name: String,
    neighbourhood: String,
    cardinalLocation: String,
    affordabilityLevel: String,
) {
    Row(modifier = modifier) {
        ElevatedCard(
            onClick = onClick,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .border(
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(12.dp),
                ),
        ) {
            Row(
                modifier = modifier
                    .background(Color.LightGray)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = imageRes ?: R.drawable.baseline_no_photography_24),
                    contentDescription = name,
                    modifier = Modifier
                        .size(108.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(12.dp),
                        ),
                    contentScale = ContentScale.Crop,
                )
                Spacer(Modifier.size(16.dp))
                TextDetails(
                    name = name,
                    neighbourhood = neighbourhood,
                    cardinalLocation = cardinalLocation,
                    affordabilityLevel = affordabilityLevel,
                )
            }
        }
    }
}

@Composable
private fun TextDetails(
    name: String,
    neighbourhood: String,
    cardinalLocation: String,
    affordabilityLevel: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        FieldValueText(field = "name", value = name)
        FieldValueText(field = "direction", value = cardinalLocation)
        FieldValueText(field = "neighbourhood", value = neighbourhood)
        FieldValueText(field = "affordability", value = affordabilityLevel)
    }
}

@Composable
private fun FieldValueText(
    field: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(
            text = "$field: ",
            style = Typography.labelLarge,
            fontSize = TextUnit(value = 4f, type = TextUnitType.Em),
            softWrap = false,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(Modifier.size(4.dp))
        Text(
            text = value,
            style = Typography.bodySmall,
            fontSize = TextUnit(value = 3.5f, type = TextUnitType.Em),
            softWrap = false,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceCardPreview() = LondonAppTheme {
    PlaceCard(
        onClick = {},
        imageRes = R.drawable.hyde_park,
        name = "Hyde Park",
        neighbourhood = "Hyde Park, below Paddington, west of Soho, above Kensington, east of Shepherd's Bush",
        cardinalLocation = "West",
        affordabilityLevel = "Free"
    )
}
