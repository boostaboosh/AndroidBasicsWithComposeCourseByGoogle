package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeQuadrantTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    FourQuadrants()
                }
            }
        }
    }
}

@Composable
fun FourQuadrants(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.weight(1.0F)
        ) {
            Quadrant(
                modifier = Modifier
                    .weight(1.0F)
                    .background(colorResource(R.color.top_left_quadrant_color)),
                title = stringResource(R.string.text_composable_quadrant_title),
                description = stringResource(R.string.text_composable_description)
            )
            Quadrant(
                modifier = Modifier
                    .weight(1.0F)
                    .background(colorResource(R.color.top_right_quadrant_color)),
                title = stringResource(R.string.image_composable_title),
                description = stringResource(R.string.image_composable_description)
            )
        }
        Row(
            modifier = Modifier.weight(1.0F)
        ) {
            Quadrant(
                modifier = Modifier
                    .weight(1.0F)
                    .background(colorResource(R.color.bottom_left_quadrant_color)),
                title = stringResource(R.string.row_composable_title),
                description = stringResource(R.string.row_composable_description)
            )
            Quadrant(
                modifier = Modifier
                    .weight(1.0F)
                    .background(colorResource(R.color.bottom_right_quadrant_color)),
                title = stringResource(R.string.column_composable_title),
                description = stringResource(R.string.column_composable_description)
            )
        }
    }
}

@Composable
fun Quadrant(modifier: Modifier = Modifier, title: String, description: String) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold,
            text = title
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FourQuadrantsPreview() {
    ComposeQuadrantTheme {
        FourQuadrants()
    }
}
