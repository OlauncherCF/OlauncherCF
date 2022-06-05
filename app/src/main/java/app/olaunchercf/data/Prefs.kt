package app.olaunchercf.data

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.view.Gravity
import androidx.appcompat.app.AppCompatDelegate
import java.util.*

class Prefs(context: Context) {

    private val APP_LANGUAGE = "app_language"

    private val PREFS_FILENAME = "app.olauncher"

    private val FIRST_OPEN = "FIRST_OPEN"
    private val FIRST_SETTINGS_OPEN = "FIRST_SETTINGS_OPEN"
    private val FIRST_HIDE = "FIRST_HIDE"
    private val LOCK_MODE = "LOCK_MODE"
    private val HOME_APPS_NUM = "HOME_APPS_NUM"
    private val AUTO_SHOW_KEYBOARD = "AUTO_SHOW_KEYBOARD"
    private val HOME_ALIGNMENT = "HOME_ALIGNMENT"
    private val APP_LABEL_ALIGNMENT = "APP_LABEL_ALIGNMENT"
    private val STATUS_BAR = "STATUS_BAR"
    private val DATE_TIME = "DATE_TIME"
    private val SWIPE_LEFT_ENABLED = "SWIPE_LEFT_ENABLED"
    private val SWIPE_RIGHT_ENABLED = "SWIPE_RIGHT_ENABLED"
    private val SCREEN_TIMEOUT = "SCREEN_TIMEOUT"
    private val HIDDEN_APPS = "HIDDEN_APPS"
    private val HIDDEN_APPS_UPDATED = "HIDDEN_APPS_UPDATED"
    private val SHOW_HINT_COUNTER = "SHOW_HINT_COUNTER"
    private val APP_THEME = "APP_THEME"
    private val ABOUT_CLICKED = "ABOUT_CLICKED"
    private val RATE_CLICKED = "RATE_CLICKED"

    private val APP_NAME = "APP_NAME"
    private val APP_PACKAGE = "APP_PACKAGE"
    private val APP_ALIAS = "APP_USER"
    private val APP_ACTIVITY = "APP_ACTIVITY"
    /*private val APP_NAME_1 = "APP_NAME_1"
    private val APP_NAME_2 = "APP_NAME_2"
    private val APP_NAME_3 = "APP_NAME_3"
    private val APP_NAME_4 = "APP_NAME_4"
    private val APP_NAME_5 = "APP_NAME_5"
    private val APP_NAME_6 = "APP_NAME_6"
    private val APP_NAME_7 = "APP_NAME_7"
    private val APP_NAME_8 = "APP_NAME_8"
    private val APP_PACKAGE_1 = "APP_PACKAGE_1"
    private val APP_PACKAGE_2 = "APP_PACKAGE_2"
    private val APP_PACKAGE_3 = "APP_PACKAGE_3"
    private val APP_PACKAGE_4 = "APP_PACKAGE_4"
    private val APP_PACKAGE_5 = "APP_PACKAGE_5"
    private val APP_PACKAGE_6 = "APP_PACKAGE_6"
    private val APP_PACKAGE_7 = "APP_PACKAGE_7"
    private val APP_PACKAGE_8 = "APP_PACKAGE_8"
    private val APP_USER_1 = "APP_USER_1"
    private val APP_USER_2 = "APP_USER_2"
    private val APP_USER_3 = "APP_USER_3"
    private val APP_USER_4 = "APP_USER_4"
    private val APP_USER_5 = "APP_USER_5"
    private val APP_USER_6 = "APP_USER_6"
    private val APP_USER_7 = "APP_USER_7"
    private val APP_USER_8 = "APP_USER_8"*/
    /*private val APP_ACTIVITY_1 = "APP_ACTIVITY_1"
    private val APP_ACTIVITY_2 = "APP_ACTIVITY_2"
    private val APP_ACTIVITY_3 = "APP_ACTIVITY_3"
    private val APP_ACTIVITY_4 = "APP_ACTIVITY_4"
    private val APP_ACTIVITY_5 = "APP_ACTIVITY_5"
    private val APP_ACTIVITY_6 = "APP_ACTIVITY_6"
    private val APP_ACTIVITY_7 = "APP_ACTIVITY_7"
    private val APP_ACTIVITY_8 = "APP_ACTIVITY_8"*/

    private val APP_NAME_SWIPE_LEFT = "APP_NAME_SWIPE_LEFT"
    private val APP_NAME_SWIPE_RIGHT = "APP_NAME_SWIPE_RIGHT"
    private val APP_NAME_CLICK_CLOCK = "APP_NAME_CLICK_CLOCK"
    private val APP_NAME_CLICK_DATE = "APP_NAME_CLICK_DATE"

    private val APP_PACKAGE_SWIPE_LEFT = "APP_PACKAGE_SWIPE_LEFT"
    private val APP_PACKAGE_SWIPE_RIGHT = "APP_PACKAGE_SWIPE_RIGHT"
    private val APP_PACKAGE_CLICK_CLOCK = "APP_PACKAGE_CLICK_CLOCK"
    private val APP_PACKAGE_CLICK_DATE = "APP_PACKAGE_CLICK_DATE"

    private val APP_USER_SWIPE_LEFT = "APP_USER_SWIPE_LEFT"
    private val APP_USER_SWIPE_RIGHT = "APP_USER_SWIPE_RIGHT"
    private val APP_USER_CLICK_CLOCK = "APP_USER_CLICK_CLOCK"
    private val APP_USER_CLICK_DATE = "APP_USER_CLICK_DATE"

    private val APP_ACTIVITY_SWIPE_LEFT = "APP_ACTIVITY_SWIPE_LEFT"
    private val APP_ACTIVITY_SWIPE_RIGHT = "APP_ACTIVITY_SWIPE_RIGHT"
    private val APP_ACTIVITY_CLICK_CLOCK = "APP_ACTIVITY_CLICK_CLOCK"
    private val APP_ACTIVITY_CLICK_DATE = "APP_ACTIVITY_CLICK_DATE"

    private val TEXT_SIZE = "text_size"

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var firstOpen: Boolean
        get() = prefs.getBoolean(FIRST_OPEN, true)
        set(value) = prefs.edit().putBoolean(FIRST_OPEN, value).apply()

    var firstSettingsOpen: Boolean
        get() = prefs.getBoolean(FIRST_SETTINGS_OPEN, true)
        set(value) = prefs.edit().putBoolean(FIRST_SETTINGS_OPEN, value).apply()

    var firstHide: Boolean
        get() = prefs.getBoolean(FIRST_HIDE, true)
        set(value) = prefs.edit().putBoolean(FIRST_HIDE, value).apply()

    var lockModeOn: Boolean
        get() = prefs.getBoolean(LOCK_MODE, false)
        set(value) = prefs.edit().putBoolean(LOCK_MODE, value).apply()

    var autoShowKeyboard: Boolean
        get() = prefs.getBoolean(AUTO_SHOW_KEYBOARD, true)
        set(value) = prefs.edit().putBoolean(AUTO_SHOW_KEYBOARD, value).apply()

    var homeAppsNum: Int
        get() = prefs.getInt(HOME_APPS_NUM, 4)
        set(value) = prefs.edit().putInt(HOME_APPS_NUM, value).apply()

    var homeAlignment: Int
        get() = prefs.getInt(HOME_ALIGNMENT, Gravity.START)
        set(value) = prefs.edit().putInt(HOME_ALIGNMENT, value).apply()

    var appLabelAlignment: Int
        get() = prefs.getInt(APP_LABEL_ALIGNMENT, Gravity.START)
        set(value) = prefs.edit().putInt(APP_LABEL_ALIGNMENT, value).apply()

    var showStatusBar: Boolean
        get() = prefs.getBoolean(STATUS_BAR, false)
        set(value) = prefs.edit().putBoolean(STATUS_BAR, value).apply()

    var showDateTime: Boolean
        get() = prefs.getBoolean(DATE_TIME, true)
        set(value) = prefs.edit().putBoolean(DATE_TIME, value).apply()

    var swipeLeftEnabled: Boolean
        get() = prefs.getBoolean(SWIPE_LEFT_ENABLED, true)
        set(value) = prefs.edit().putBoolean(SWIPE_LEFT_ENABLED, value).apply()

    var swipeRightEnabled: Boolean
        get() = prefs.getBoolean(SWIPE_RIGHT_ENABLED, true)
        set(value) = prefs.edit().putBoolean(SWIPE_RIGHT_ENABLED, value).apply()

    var appTheme: Int
        get() = prefs.getInt(APP_THEME, AppCompatDelegate.MODE_NIGHT_YES)
        set(value) = prefs.edit().putInt(APP_THEME, value).apply()

    var language: String
        get() = prefs.getString(APP_LANGUAGE, Constants.LANG_EN).toString()
        set(value) = prefs.edit().putString(APP_LANGUAGE, value).apply()

    var screenTimeout: Int
        get() = prefs.getInt(SCREEN_TIMEOUT, 30000) // Default: 30 seconds
        set(value) = prefs.edit().putInt(SCREEN_TIMEOUT, value).apply()

    var hiddenApps: MutableSet<String>
        get() = prefs.getStringSet(HIDDEN_APPS, mutableSetOf()) as MutableSet<String>
        set(value) = prefs.edit().putStringSet(HIDDEN_APPS, value).apply()

    var hiddenAppsUpdated: Boolean
        get() = prefs.getBoolean(HIDDEN_APPS_UPDATED, false)
        set(value) = prefs.edit().putBoolean(HIDDEN_APPS_UPDATED, value).apply()

    var toShowHintCounter: Int
        get() = prefs.getInt(SHOW_HINT_COUNTER, 1)
        set(value) = prefs.edit().putInt(SHOW_HINT_COUNTER, value).apply()

    fun getHomeAppValues(i: Int): Array<String> {
        val nameId = "${APP_NAME}_$i"
        val propId = "${APP_PACKAGE}_$i"
        val aliasId = "${APP_ALIAS}_$i"
        val activityId = "${APP_ACTIVITY}_$i"

        val name = prefs.getString(nameId, "").toString()
        val prop = prefs.getString(propId, "").toString()
        val alias = prefs.getString(aliasId, "").toString()
        val activity = prefs.getString(activityId, "").toString()

        return arrayOf(name, prop, alias, activity)
    }

    fun setHomeAppValues(i: Int, name: String, prop: String, alias: String, activity: String) {
        val nameId = "${APP_NAME}_$i"
        val propId = "${APP_PACKAGE}_$i"
        val aliasId = "${APP_ALIAS}_$i"

        prefs.edit().putString(nameId, name).apply()
        prefs.edit().putString(propId, prop).apply()
        prefs.edit().putString(aliasId, alias).apply()
        prefs.edit().putString(aliasId, activity).apply()
    }

    fun setHomeAppName(i: Int, name: String) {
        val nameId = "${APP_NAME}_$i"
        prefs.edit().putString(nameId, name).apply()
    }

    // this only resets name and alias because of how this was done before in HomeFragment
    fun resetHomeAppValues(i: Int) {
        val nameId = "${APP_NAME}_$i"
        val aliasId = "${APP_ALIAS}_$i"

        prefs.edit().putString(nameId, "").apply()
        prefs.edit().putString(aliasId, "").apply()
    }

    /*var appName1: String
        get() = prefs.getString(APP_NAME_1, "").toString()
        set(value) = prefs.edit().putString(APP_NAME_1, value).apply()

    var appName2: String
        get() = prefs.getString(APP_NAME_2, "").toString()
        set(value) = prefs.edit().putString(APP_NAME_2, value).apply()

    var appName3: String
        get() = prefs.getString(APP_NAME_3, "").toString()
        set(value) = prefs.edit().putString(APP_NAME_3, value).apply()

    var appName4: String
        get() = prefs.getString(APP_NAME_4, "").toString()
        set(value) = prefs.edit().putString(APP_NAME_4, value).apply()

    var appName5: String
        get() = prefs.getString(APP_NAME_5, "").toString()
        set(value) = prefs.edit().putString(APP_NAME_5, value).apply()

    var appName6: String
        get() = prefs.getString(APP_NAME_6, "").toString()
        set(value) = prefs.edit().putString(APP_NAME_6, value).apply()

    var appName7: String
        get() = prefs.getString(APP_NAME_7, "").toString()
        set(value) = prefs.edit().putString(APP_NAME_7, value).apply()

    var appName8: String
        get() = prefs.getString(APP_NAME_8, "").toString()
        set(value) = prefs.edit().putString(APP_NAME_8, value).apply()

    var appPackage1: String
        get() = prefs.getString(APP_PACKAGE_1, "").toString()
        set(value) = prefs.edit().putString(APP_PACKAGE_1, value).apply()

    var appPackage2: String
        get() = prefs.getString(APP_PACKAGE_2, "").toString()
        set(value) = prefs.edit().putString(APP_PACKAGE_2, value).apply()

    var appPackage3: String
        get() = prefs.getString(APP_PACKAGE_3, "").toString()
        set(value) = prefs.edit().putString(APP_PACKAGE_3, value).apply()

    var appPackage4: String
        get() = prefs.getString(APP_PACKAGE_4, "").toString()
        set(value) = prefs.edit().putString(APP_PACKAGE_4, value).apply()

    var appPackage5: String
        get() = prefs.getString(APP_PACKAGE_5, "").toString()
        set(value) = prefs.edit().putString(APP_PACKAGE_5, value).apply()

    var appPackage6: String
        get() = prefs.getString(APP_PACKAGE_6, "").toString()
        set(value) = prefs.edit().putString(APP_PACKAGE_6, value).apply()

    var appPackage7: String
        get() = prefs.getString(APP_PACKAGE_7, "").toString()
        set(value) = prefs.edit().putString(APP_PACKAGE_7, value).apply()

    var appPackage8: String
        get() = prefs.getString(APP_PACKAGE_8, "").toString()
        set(value) = prefs.edit().putString(APP_PACKAGE_8, value).apply()

    var appUser1: String
        get() = prefs.getString(APP_USER_1, "").toString()
        set(value) = prefs.edit().putString(APP_USER_1, value).apply()

    var appUser2: String
        get() = prefs.getString(APP_USER_2, "").toString()
        set(value) = prefs.edit().putString(APP_USER_2, value).apply()

    var appUser3: String
        get() = prefs.getString(APP_USER_3, "").toString()
        set(value) = prefs.edit().putString(APP_USER_3, value).apply()

    var appUser4: String
        get() = prefs.getString(APP_USER_4, "").toString()
        set(value) = prefs.edit().putString(APP_USER_4, value).apply()

    var appUser5: String
        get() = prefs.getString(APP_USER_5, "").toString()
        set(value) = prefs.edit().putString(APP_USER_5, value).apply()

    var appUser6: String
        get() = prefs.getString(APP_USER_6, "").toString()
        set(value) = prefs.edit().putString(APP_USER_6, value).apply()

    var appUser7: String
        get() = prefs.getString(APP_USER_7, "").toString()
        set(value) = prefs.edit().putString(APP_USER_7, value).apply()

    var appUser8: String
        get() = prefs.getString(APP_USER_8, "").toString()
        set(value) = prefs.edit().putString(APP_USER_8, value).apply()*/

    /*var appActivity1: String
        get() = prefs.getString(APP_ACTIVITY_1, "").toString()
        set(value) = prefs.edit().putString(APP_ACTIVITY_1, value).apply()

    var appActivity2: String
        get() = prefs.getString(APP_ACTIVITY_2, "").toString()
        set(value) = prefs.edit().putString(APP_ACTIVITY_2, value).apply()

    var appActivity3: String
        get() = prefs.getString(APP_ACTIVITY_3, "").toString()
        set(value) = prefs.edit().putString(APP_ACTIVITY_3, value).apply()

    var appActivity4: String
        get() = prefs.getString(APP_ACTIVITY_4, "").toString()
        set(value) = prefs.edit().putString(APP_ACTIVITY_4, value).apply()

    var appActivity5: String
        get() = prefs.getString(APP_ACTIVITY_5, "").toString()
        set(value) = prefs.edit().putString(APP_ACTIVITY_5, value).apply()

    var appActivity6: String
        get() = prefs.getString(APP_ACTIVITY_6, "").toString()
        set(value) = prefs.edit().putString(APP_ACTIVITY_6, value).apply()

    var appActivity7: String
        get() = prefs.getString(APP_ACTIVITY_7, "").toString()
        set(value) = prefs.edit().putString(APP_ACTIVITY_7, value).apply()

    var appActivity8: String
        get() = prefs.getString(APP_ACTIVITY_8, "").toString()
        set(value) = prefs.edit().putString(APP_ACTIVITY_8, value).apply()*/

    var appNameSwipeLeft: String
        get() = prefs.getString(APP_NAME_SWIPE_LEFT, "Camera").toString()
        set(value) = prefs.edit().putString(APP_NAME_SWIPE_LEFT, value).apply()

    var appNameSwipeRight: String
        get() = prefs.getString(APP_NAME_SWIPE_RIGHT, "Phone").toString()
        set(value) = prefs.edit().putString(APP_NAME_SWIPE_RIGHT, value).apply()

    var appNameClickClock: String
        get() = prefs.getString(APP_NAME_CLICK_CLOCK, "Clock").toString()
        set(value) = prefs.edit().putString(APP_NAME_CLICK_CLOCK, value).apply()

    var appNameClickDate: String
        get() = prefs.getString(APP_NAME_CLICK_DATE, "Calendar").toString()
        set(value) = prefs.edit().putString(APP_NAME_CLICK_DATE, value).apply()

    var appPackageSwipeLeft: String
        get() = prefs.getString(APP_PACKAGE_SWIPE_LEFT, "").toString()
        set(value) = prefs.edit().putString(APP_PACKAGE_SWIPE_LEFT, value).apply()

    var appPackageSwipeRight: String
        get() = prefs.getString(APP_PACKAGE_SWIPE_RIGHT, "").toString()
        set(value) = prefs.edit().putString(APP_PACKAGE_SWIPE_RIGHT, value).apply()

    var appPackageClickClock: String
        get() = prefs.getString(APP_PACKAGE_CLICK_CLOCK, "").toString()
        set(value) = prefs.edit().putString(APP_PACKAGE_CLICK_CLOCK, value).apply()

    var appPackageClickDate: String
        get() = prefs.getString(APP_PACKAGE_CLICK_DATE, "").toString()
        set(value) = prefs.edit().putString(APP_PACKAGE_CLICK_DATE, value).apply()

    var appUserSwipeLeft: String
        get() = prefs.getString(APP_USER_SWIPE_LEFT, "").toString()
        set(value) = prefs.edit().putString(APP_USER_SWIPE_LEFT, value).apply()

    var appUserSwipeRight: String
        get() = prefs.getString(APP_USER_SWIPE_RIGHT, "").toString()
        set(value) = prefs.edit().putString(APP_USER_SWIPE_RIGHT, value).apply()

    var appUserClickClock: String
        get() = prefs.getString(APP_USER_CLICK_CLOCK, "").toString()
        set(value) = prefs.edit().putString(APP_USER_CLICK_CLOCK, value).apply()

    var appUserClickDate: String
        get() = prefs.getString(APP_USER_CLICK_DATE, "").toString()
        set(value) = prefs.edit().putString(APP_USER_CLICK_DATE, value).apply()

    var appActivitySwipeLeft: String
        get() = prefs.getString(APP_ACTIVITY_SWIPE_LEFT, "").toString()
        set(value) = prefs.edit().putString(APP_ACTIVITY_SWIPE_LEFT, value).apply()

    var appActivitySwipeRight: String
        get() = prefs.getString(APP_ACTIVITY_SWIPE_RIGHT, "").toString()
        set(value) = prefs.edit().putString(APP_ACTIVITY_SWIPE_RIGHT, value).apply()

    var appActivityClickClock: String
        get() = prefs.getString(APP_ACTIVITY_CLICK_CLOCK, "").toString()
        set(value) = prefs.edit().putString(APP_ACTIVITY_CLICK_CLOCK, value).apply()

    var appActivityClickDate: String
        get() = prefs.getString(APP_ACTIVITY_CLICK_DATE, "").toString()
        set(value) = prefs.edit().putString(APP_ACTIVITY_CLICK_DATE, value).apply()

    var textSize: Float
        get() = prefs.getFloat(TEXT_SIZE, Constants.TEXT_SIZE_NORMAL)
        set(value) = prefs.edit().putFloat(TEXT_SIZE, value).apply()

    fun getAppName(location: Int): String {
        val (name, _, _, _) = this.getHomeAppValues(location)
        return name
        /*return when (location) {
            1 -> prefs.getString(APP_NAME_1, "").toString()
            2 -> prefs.getString(APP_NAME_2, "").toString()
            3 -> prefs.getString(APP_NAME_3, "").toString()
            4 -> prefs.getString(APP_NAME_4, "").toString()
            5 -> prefs.getString(APP_NAME_5, "").toString()
            6 -> prefs.getString(APP_NAME_6, "").toString()
            7 -> prefs.getString(APP_NAME_7, "").toString()
            8 -> prefs.getString(APP_NAME_8, "").toString()
            else -> ""
        }*/
    }

    fun getAppAlias(appName: String): String {
        return prefs.getString(appName, "").toString()
    }
    fun setAppAlias(appName: String, appAlias: String) {
        prefs.edit().putString(appName, appAlias).apply()
    }

    fun getAppPackage(location: Int): String {
        val (_, pack, _, _) = this.getHomeAppValues(location)
        return pack
        /*return when (location) {
            1 -> prefs.getString(APP_PACKAGE_1, "").toString()
            2 -> prefs.getString(APP_PACKAGE_2, "").toString()
            3 -> prefs.getString(APP_PACKAGE_3, "").toString()
            4 -> prefs.getString(APP_PACKAGE_4, "").toString()
            5 -> prefs.getString(APP_PACKAGE_5, "").toString()
            6 -> prefs.getString(APP_PACKAGE_6, "").toString()
            7 -> prefs.getString(APP_PACKAGE_7, "").toString()
            8 -> prefs.getString(APP_PACKAGE_8, "").toString()
            else -> ""
        }*/
    }

    fun getAppUser(location: Int): String {
        val (_, _, alias, _) = this.getHomeAppValues(location)
        return alias
        /*return when (location) {
            1 -> prefs.getString(APP_USER_1, "").toString()
            2 -> prefs.getString(APP_USER_2, "").toString()
            3 -> prefs.getString(APP_USER_3, "").toString()
            4 -> prefs.getString(APP_USER_4, "").toString()
            5 -> prefs.getString(APP_USER_5, "").toString()
            6 -> prefs.getString(APP_USER_6, "").toString()
            7 -> prefs.getString(APP_USER_7, "").toString()
            8 -> prefs.getString(APP_USER_8, "").toString()
            else -> ""
        }*/
    }

    fun getAppActivity(location: Int): String {
        val (_, _, _, activity) = this.getHomeAppValues(location)
        return activity
        /*return when (location) {
            1 -> prefs.getString(APP_ACTIVITY_1, "").toString()
            2 -> prefs.getString(APP_ACTIVITY_2, "").toString()
            3 -> prefs.getString(APP_ACTIVITY_3, "").toString()
            4 -> prefs.getString(APP_ACTIVITY_4, "").toString()
            5 -> prefs.getString(APP_ACTIVITY_5, "").toString()
            6 -> prefs.getString(APP_ACTIVITY_6, "").toString()
            7 -> prefs.getString(APP_ACTIVITY_7, "").toString()
            8 -> prefs.getString(APP_ACTIVITY_8, "").toString()
            else -> ""
        }*/
    }
}