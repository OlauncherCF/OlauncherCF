package app.olaunchercf

import android.content.SharedPreferences
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.Navigation.findNavController
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.CursorMatchers.withRowString
import androidx.test.espresso.matcher.ViewMatchers.*
import app.olaunchercf.data.AppModel
import app.olaunchercf.data.Constants
import app.olaunchercf.data.Constants.PREFS_FILENAME
import app.olaunchercf.data.Prefs
import app.olaunchercf.ui.AppDrawerAdapter
import org.hamcrest.core.IsAnything.anything
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SettingsTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private lateinit var prefs: Prefs

    @Before
    fun clearSharedPreferences() {
        prefs = Prefs(composeTestRule.activity)
        val shPrefs: SharedPreferences = composeTestRule.activity.getSharedPreferences(PREFS_FILENAME, 0)
        shPrefs.edit().clear().commit()
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
    fun hideClock() {
        goToSettings()
        composeTestRule.onNode(hasAnySibling(hasText("Show time"))).performClick()
        assert(!prefs.showTime)
    }

    @Test
    fun hideDate() {
        goToSettings()
        composeTestRule.onNode(hasAnySibling(hasText("Show date"))).performClick()
        assert(!prefs.showDate)
    }

    @Test
    fun testSwipeLeft() {
        goToSettings()
        composeTestRule.onNode(hasAnySibling(hasText("Swipe left app"))).performClick()
        pickAppFromList()
        assert(prefs.appSwipeLeft.name == "Chrome")
    }

    @Test
    fun testSwipeRight() {
        goToSettings()
        composeTestRule.onNode(hasAnySibling(hasText("Swipe right app"))).performClick()
        pickAppFromList()
        assert(Prefs(composeTestRule.activity).appSwipeRight.name == "Chrome")
    }

    @Test
    fun testClickClock() {
        goToSettings()
        composeTestRule.onNode(hasAnySibling(hasText("Clock click app"))).performClick()
        pickAppFromList()
        assert(Prefs(composeTestRule.activity).appClickClock.name == "Chrome")
    }

    @Test
    fun testClickDate() {
        goToSettings()
        composeTestRule.onNode(hasAnySibling(hasText("Date click app"))).performClick()
        pickAppFromList()
        assert(Prefs(composeTestRule.activity).appClickDate.name == "Chrome")
    }

    private fun pickAppFromList() {
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions
                .actionOnItemAtPosition<AppDrawerAdapter.ViewHolder>(3, click())
        )
    }

    private fun goToSettings() {
        composeTestRule.activityRule.scenario.onActivity {
            findNavController(it, R.id.nav_host_fragment)
                .navigate(R.id.action_mainFragment_to_settingsFragment)
        }
    }

    private fun increaseAppNumber(i: Int) {
        composeTestRule.onNode(hasAnySibling(hasText("Number of shortcuts"))).performClick()
        composeTestRule.onNodeWithText("+").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("Save").assertIsDisplayed().performClick()
        composeTestRule.onAllNodesWithText((i+1).toString())[0].assertIsDisplayed()
    }

    private fun decreaseAppNumber(i: Int) {
        composeTestRule.onAllNodesWithText(i.toString())[0].performClick()
        composeTestRule.onNodeWithText("-").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("Save").assertIsDisplayed().performClick()
        composeTestRule.onAllNodesWithText((i-1).toString())[0].assertIsDisplayed()
    }
}