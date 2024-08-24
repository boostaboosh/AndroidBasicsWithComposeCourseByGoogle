package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpace(
                    modifier = Modifier
                        .padding(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        var image by remember { mutableStateOf(R.drawable.banff) }
        var contentDescription by remember { mutableStateOf(R.string.banff_description) }
        var year by remember { mutableStateOf(R.string.banff_year) }
        var title by remember { mutableStateOf(R.string.banff_title)}

        Spacer(modifier = Modifier.size(50.dp))
        ImageFrame(image = image, contentDescription = contentDescription)
        Spacer(modifier = Modifier.size(50.dp).weight(1F))
        ImageDetailsArea(
            title = title,
            year = year,
            description = contentDescription
        )
        NavigationButtons(
            modifier = Modifier.fillMaxWidth(),
            previousButtonText = R.string.previous_button_text,
            nextButtonText = R.string.next_button_text,
            previous = {
                when (image) {
                    R.drawable.banff -> {
                        image = R.drawable.paris
                        contentDescription = R.string.paris_description
                        year = R.string.paris_year
                        title = R.string.paris_title
                    }
                    R.drawable.thailand -> {
                        image = R.drawable.banff
                        contentDescription = R.string.banff_description
                        year = R.string.banff_year
                        title = R.string.banff_title
                    }
                    R.drawable.paris -> {
                        image = R.drawable.thailand
                        contentDescription = R.string.thailand_description
                        year = R.string.thailand_year
                        title = R.string.thailand_title
                    }
                }
            },
            next = {
                when (image) {
                    R.drawable.banff -> {
                        image = R.drawable.thailand
                        contentDescription = R.string.thailand_description
                        year = R.string.thailand_year
                        title = R.string.thailand_title
                    }
                    R.drawable.thailand -> {
                        image = R.drawable.paris
                        contentDescription = R.string.paris_description
                        year = R.string.paris_year
                        title = R.string.paris_title
                    }
                    R.drawable.paris -> {
                        image = R.drawable.banff
                        contentDescription = R.string.banff_description
                        year = R.string.banff_year
                        title = R.string.banff_title
                    }
                }
            },
        )
    }
}


@Composable
fun ImageFrame(
    @DrawableRes image: Int,
    @StringRes contentDescription: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .border(width = 0.5.dp, color = Color(android.graphics.Color.BLACK))
            .padding(32.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = contentDescription)
        )
    }
}

@Composable
fun ImageDetailsArea(
    @StringRes title: Int,
    @StringRes year: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .padding(32.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(12.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = title),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = stringResource(id = year),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.End)
                )
                Text(text = stringResource(id = description))
            }
        }
    }
}

@Composable
fun NavigationButtons(
    @StringRes previousButtonText: Int,
    @StringRes nextButtonText: Int,
    previous: () -> Unit,
    next: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = previous
        ) {
            Text(text = stringResource(id = previousButtonText))
        }
        Button(
            onClick = next
        ) {
            Text(text = stringResource(id = nextButtonText))
        }
    }
}