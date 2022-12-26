package app.olaunchercf

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Secure.getString
import android.widget.ScrollView
import android.widget.TextView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.Navigation.findNavController
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import app.olaunchercf.data.Constants
import app.olaunchercf.data.Prefs
import app.olaunchercf.ui.AppDrawerAdapter
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
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
        clickOnSetting(composeTestRule.activity.getString(R.string.show_time))
        assert(!prefs.showTime)
    }

    @Test
    fun hideDate() {
        goToSettings()
        clickOnSetting(composeTestRule.activity.getString(R.string.show_date))
        assert(!prefs.showDate)
    }

    @Test
    fun testOpenApp() {
        val actions = listOf(
            R.string.date_click_app,
            R.string.clock_click_app,
            R.string.swipe_left_app,
            R.string.swipe_right_app,
            R.string.swipe_up_app,
            R.string.swipe_down_app,
        )

        goToSettings()

        for (action in actions) {
            clickOnSetting(composeTestRule.activity.getString(action))
            composeTestRule.onNode(hasText(getString(R.string.open_app))).performClick()
            pickAppFromList()

            val value = when(action) {
                R.string.date_click_app -> {
                    assert(prefs.clickDateAction == Constants.Action.OpenApp)
                    prefs.appClickDate.name
                }
                R.string.clock_click_app -> {
                    assert(prefs.clickClockAction == Constants.Action.OpenApp)
                    prefs.appClickClock.name
                }
                R.string.swipe_left_app -> {
                    assert(prefs.swipeLeftAction == Constants.Action.OpenApp)
                    prefs.appSwipeLeft.name
                }
                R.string.swipe_right_app -> {
                    assert(prefs.swipeRightAction == Constants.Action.OpenApp)
                    prefs.appSwipeRight.name
                }
                R.string.swipe_up_app -> {
                    assert(prefs.swipeUpAction == Constants.Action.OpenApp)
                    prefs.appSwipeUp.name
                }
                R.string.swipe_down_app -> {
                    assert(prefs.swipeDownAction == Constants.Action.OpenApp)
                    prefs.appSwipeDown.name
                }
                else -> {
                    assert(false)
                    return
                }
            }
            assert(value == "Camera") { "Actual value: $value" }
        }

    }

    @Test
    fun testDoubleTap() {
        val actions = listOf(
            R.string.date_click_app,
            R.string.clock_click_app,
            R.string.swipe_left_app,
            R.string.swipe_right_app,
            R.string.swipe_up_app,
            R.string.swipe_down_app,
        )


        for (action in actions) {
            goToSettings()
            clickOnSetting(composeTestRule.activity.getString(action))
            composeTestRule.onNode(hasText(getString(R.string.lock_screen))).performClick()

            /*val value = when(action) {
                R.string.date_click_app -> {
                    assert(prefs.clickDateAction == Constants.Action.OpenApp)
                    prefs.appClickDate.name
                }
                R.string.clock_click_app -> {
                    assert(prefs.clickClockAction == Constants.Action.OpenApp)
                    prefs.appClickClock.name
                }
                R.string.swipe_left_app -> {
                    assert(prefs.swipeLeftAction == Constants.Action.OpenApp)
                    prefs.appSwipeLeft.name
                }
                R.string.swipe_right_app -> {
                    assert(prefs.swipeRightAction == Constants.Action.OpenApp)
                    prefs.appSwipeRight.name
                }
                R.string.swipe_up_app -> {
                    assert(prefs.swipeUpAction == Constants.Action.OpenApp)
                    prefs.appSwipeUp.name
                }
                R.string.swipe_down_app -> {
                    assert(prefs.swipeDownAction == Constants.Action.OpenApp)
                    prefs.appSwipeDown.name
                }
                else -> {
                    assert(false)
                    return
                }
            }*/
            // assert(value == "Camera") { "Actual value: $value" }
        }

    }

    @Test
    fun backupRestore() {
        assert(true)
    }

    @Test
    fun serialize() {
        val prefs = Prefs(getApplicationContext())

        prefs.apply {
            firstOpen = false
            homeAppsNum = 10
            homeAlignment = Constants.Gravity.Center
            swipeLeftAction = Constants.Action.OpenQuickSettings
        }

        val string = prefs.saveToString()
        clearSharedPreferences()
        prefs.loadFromString(string)

        prefs.apply {
            assert(!firstOpen)
            assert(homeAppsNum == 10)
            assert(homeAlignment == Constants.Gravity.Center)
            assert(swipeLeftAction == Constants.Action.OpenQuickSettings)
        }
    }

    private fun pickAppFromList(i: Int = 4) {
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<AppDrawerAdapter.ViewHolder>(i, click())
        )
    }

    private fun clickOnSetting(name: String) {
        composeTestRule.onNode(hasAnySibling(hasText(name))).assertExists().performScrollTo().performClick()
    }

    private fun getString(id: Int): String {
        return composeTestRule.activity.getString(id)
    }

    @Test
    fun goToSettings() {
        composeTestRule.activityRule.scenario.onActivity {
            findNavController(it, R.id.nav_host_fragment).navigate(R.id.action_mainFragment_to_settingsFragment)
        }
        // composeTestRule.onNode(withId(R.id.touchArea)).performClick()
        //onView(withText("Olauncher Clutter Free")).check(matches(isDisplayed()))
        // composeTestRule.onNode(hasAnySibling(hasText("Number of shortcuts"))).assertIsDisplayed()
        onView(withId(R.id.scrollLayout)).check(matches(isDisplayed()))
    }

    private fun increaseAppNumber(i: Int) {
        composeTestRule.onNode(hasAnySibling(hasText(composeTestRule.activity.getString(R.string.apps_on_home_screen)))).assertExists().performScrollTo().performClick()
        /*val buttonMatcher = hasTestTag("test$i")
        composeTestRule.onNode(buttonMatcher).assertExists().performScrollTo().performClick()*/
        composeTestRule.onNodeWithText("+").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("Save").assertIsDisplayed().performClick()
        composeTestRule.onAllNodesWithText((i+1).toString())[0].assertIsDisplayed()
    }

    private fun decreaseAppNumber(i: Int) {
        //composeTestRule.onAllNodesWithText(i.toString())[0].performClick()
        composeTestRule.onNode(hasAnySibling(hasText(composeTestRule.activity.getString(R.string.apps_on_home_screen)))).assertExists().performScrollTo().performClick()
        composeTestRule.onNodeWithText("-").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("Save").assertIsDisplayed().performClick()
        composeTestRule.onAllNodesWithText((i-1).toString())[0].assertIsDisplayed()
    }
