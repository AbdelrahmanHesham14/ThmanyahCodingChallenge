package com.thmanyah.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.hilt.navigation.compose.hiltViewModel
import com.thmanyah.presentation.home.HomeScreen
import com.thmanyah.presentation.search.SearchScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun testHomeScreen() {

        // Start the app
        composeTestRule.setContent {
            HomeScreen(
                viewModel = hiltViewModel()
            ) {

            }
        }

        composeTestRule.onNodeWithText("Home Screen")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("search_button")
            .assertExists()
            .performClick()
    }
}