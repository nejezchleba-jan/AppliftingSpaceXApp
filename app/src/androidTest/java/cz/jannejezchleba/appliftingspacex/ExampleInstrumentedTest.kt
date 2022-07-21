package cz.jannejezchleba.appliftingspacex

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import cz.jannejezchleba.appliftingspacex.ui.MainActivity
import cz.jannejezchleba.appliftingspacex.ui.AppMainScreen
import cz.jannejezchleba.appliftingspacex.ui.launches.LaunchesScreen
import cz.jannejezchleba.appliftingspacex.ui.theme.AppliftingSpaceXTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_menu_button_opens_drawer() {
        val activity = composeTestRule.activity
        composeTestRule.setContent {
            AppliftingSpaceXTheme {
                val emptyNavController = NavController(activity.applicationContext)
                AppMainScreen(emptyNavController,"", false) {}
            }
        }

        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.DESC_MENU)).performClick()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.DESC_SPACEX_DRAWER)).assertIsDisplayed()
    }
}
