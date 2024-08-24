package com.example.artspace

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.artspace.ui.theme.ArtSpaceTheme
import org.junit.Rule
import org.junit.Test

class GalleryUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun go_to_next_picture() {
        composeTestRule.setContent {
            ArtSpaceTheme {
                ArtSpace()
            }
        }
        composeTestRule.onNodeWithText("next").performClick()
        composeTestRule.onNodeWithText("Beautiful Thai Beach")
            .assertExists("No node with this text was found")
    }
}