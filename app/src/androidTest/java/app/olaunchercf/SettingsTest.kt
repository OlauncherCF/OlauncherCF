package app.olaunchercf

import android.content.SharedPreferences
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.Navigation.findNavController
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.withId
import app.olaunchercf.data.Constants
import app.olaunchercf.data.Constants.PREFS_FILENAME
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SettingsTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun clearSharedPreferences() {
        val prefs: SharedPreferences = composeTestRule.activity.getSharedPreferences(PREFS_FILENAME, 0)
        prefs.edit().clear().commit()
    }

    @Test
    fun testNumberAppsHomeScreen() {
        for (i in 4 until Constants.MAX_HOME_APPS) {
            goToSettings()
            increaseAppNumber(i)
            Espresso.pressBack()
            onView(withId(R.id.homeAppsLayout)).check(matches(hasChildCount(i+1)))
        }
        for (i in Constants.MAX_HOME_APPS downTo 1) {
            goToSettings()
            decreaseAppNumber(i)
            Espresso.pressBack()
            onView(withId(R.id.homeAppsLayout)).check(matches(hasChildCount(i-1)))
        }
        for (i in 0 until 4) {
            goToSettings()
            increaseAppNumber(i)
            Espresso.pressBack()
            onView(withId(R.id.homeAppsLayout)).check(matches(hasChildCount(i+1)))
        }
    }

    @Test
    fun testAlignment() {
        goToSettings()
        composeTestRule.onAllNodesWithText("left")
    }

    private fun goToSettings() {
        composeTestRule.activityRule.scenario.onActivity {
            findNavController(it, R.id.nav_host_fragment)
                .navigate(R.id.action_mainFragment_to_settingsFragment)
        }
    }

    private fun increaseAppNumber(i: Int) {
        composeTestRule.onAllNodesWithText(i.toString())[0].performClick()
        composeTestRule.onNodeWithText("+").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("Commit").assertIsDisplayed().performClick()
        composeTestRule.onAllNodesWithText((i+1).toString())[0].assertIsDisplayed()
    }

    private fun decreaseAppNumber(i: Int) {
        composeTestRule.onAllNodesWithText(i.toString())[0].performClick()
        composeTestRule.onNodeWithText("-").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("Commit").assertIsDisplayed().performClick()
        composeTestRule.onAllNodesWithText((i-1).toString())[0].assertIsDisplayed()
    }
}