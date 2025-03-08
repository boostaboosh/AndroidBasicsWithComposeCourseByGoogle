package com.example.londonapp.ui.stateConsumers.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.londonapp.R
import com.example.londonapp.ui.stateConsumers.theme.LondonAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBackBar(
    text: String,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = { Text(text = text) },
            navigationIcon = {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_to_home_screen),
                    )
                }
            }
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color(alpha = 0xFF, red = 0x00, green = 0x00, blue = 0x00)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBackBarPreview() = LondonAppTheme {
    TopBackBar(text = "a screen", onBackPressed = {})
}
