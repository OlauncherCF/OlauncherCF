package app.olaunchercf

import android.content.SharedPreferences
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.CursorMatchers.withRowString
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import app.olaunchercf.data.AppModel
import app.olaunchercf.data.Constants
import app.olaunchercf.data.Prefs
import app.olaunchercf.ui.AppDrawerAdapter
import app.olaunchercf.ui.HomeFragment
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
        val shPrefs: SharedPreferences = composeTestRule.activity.getSharedPreferences("app.olauncher", 0)
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

    @Test
    fun goToSettings() {
        // Create a TestNavHostController
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        // Create a graphical FragmentScenario for the TitleScreen
        val titleScenario = launchFragmentInContainer<HomeFragment>()
        titleScenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.nav_graph)
            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        onView(withId(R.id.touchArea)).perform(longClick())
        assert(navController.currentDestination?.id == R.id.settingsFragment)
        //assertThat(navController.currentDestination?.id).isEqualTo(R.id.in_game)

        /*composeTestRule.activityRule.scenario.onActivity {
            //findNavController(it, R.id.nav_host_fragment).navigate(R.id.action_mainFragment_to_settingsFragment)
            findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
        }*/
        composeTestRule.onNode(hasAnySibling(hasText("Number of shortcuts"))).assertIsDisplayed()
    }

    private fun increaseAppNumber(i: Int) {
        composeTestRule.onNode(hasAnySibling(hasText("Number of shortcuts"))).assertIsDisplayed().performClick()
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