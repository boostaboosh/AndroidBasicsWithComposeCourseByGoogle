package com.example.londonapp.ui.stateConsumers.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
    }
}

@Preview(showBackground = true)
@Composable
fun TopBackBarPreview() = LondonAppTheme {
    TopBackBar(text = "a screen", onBackPressed = {})
}
