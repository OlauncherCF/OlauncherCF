import android.content.Context
import androidx.compose.ui.test.MainTestClock
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import app.olaunchercf.MainActivity
import org.junit.Rule
import org.junit.Test
import app.olaunchercf.R
import app.olaunchercf.ui.compose.SettingsComposable

class ComposeTest {
    @Rule
    @JvmField
    // val composeTestRule = createComposeRule()
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun myTest() {
        val targetContext = ApplicationProvider.getApplicationContext<Context>()
        val test: String =  targetContext.resources.getString(R.layout.fragment_home)
        composeTestRule.onNodeWithText(test).assertIsDisplayed()

        // val greeting = ApplicationProvider.getInstrumentation().targetContext.resources.getString(R.string.greeting)
        // composeTestRule.onNodeWithText(greeting).assertIsDisplayed()

    }
}