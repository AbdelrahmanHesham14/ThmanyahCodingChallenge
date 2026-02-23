package com.thmanyah.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.thmanyah.presentation.search.SearchScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule val composeTestRule = createAndroidComposeRule<HiltTestActivity>()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun testSearchScreen() {

        // Start the app
        composeTestRule.setContent {
            SearchScreen(
                viewModel = hiltViewModel()
            ) {

            }
        }

        composeTestRule.onNodeWithText("Search Screen")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("query_field")
            .assertExists()
            .assertIsDisplayed()

        val testText = "Compose Test"

        composeTestRule
            .onNodeWithTag("query_field")
            .performTextInput(testText)

        // Assert: text field displays what was typed
        composeTestRule
            .onNodeWithTag("query_field")
            .assertTextEquals(testText)

        composeTestRule
            .onNodeWithTag("back_button")
            .assertExists()
            .performClick()
    }
}