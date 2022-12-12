package app.olaunchercf.data

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import app.olaunchercf.R
import java.util.*

interface EnumOption {
    @Composable
    fun string(): String
}

object Constants {

    /*const val FLAG_LAUNCH_APP = 100
    const val FLAG_HIDDEN_APPS = 101

    const val FLAG_SET_HOME_APP = 1

    const val FLAG_SET_SWIPE_LEFT_APP = 11
    const val FLAG_SET_SWIPE_RIGHT_APP = 12

    const val FLAG_SET_CLICK_CLOCK_APP = 13
    const val FLAG_SET_CLICK_DATE_APP = 14*/

    const val REQUEST_CODE_ENABLE_ADMIN = 666

    const val TRIPLE_TAP_DELAY_MS = 300
    const val LONG_PRESS_DELAY_MS = 500

    const val MAX_HOME_APPS = 15
    const val TEXT_SIZE_MIN = 10
    const val TEXT_SIZE_MAX = 60

    enum class AppDrawerFlag {
        LaunchApp,
        HiddenApps,
        SetHomeApp,
        SetSwipeLeft,
        SetSwipeRight,
        SetSwipeDown,
        SetClickClock,
        SetClickDate,
        SetDoubleTap,
    }

    enum class Language: EnumOption {
        System,
        Arabic,
        Chinese,
        Croatian,
        Dutch,
        English,
        Estonian,
        French,
        German,
        Greek,
        Indonesian,
        Italian,
        Korean,
        Persian,
        Portuguese,
        Russian,
        Spanish,
        Swedish,
        Turkish;

        @Composable
        override fun string(): String {
            return when(this) {
                System -> stringResource(R.string.lang_system)
                Arabic -> "العربية"
                Chinese -> "中文"
                Croatian -> "Hrvatski"
                Dutch -> "Nederlands"
                English -> "English"
                Estonian -> "Eesti keel"
                French -> "Français"
                German -> "Deutsch"
                Greek -> "Ελληνική"
                Indonesian -> "Bahasa Indonesia"
                Italian -> "Italiano"
                Korean -> "한국어"
                Persian -> "فارسی"
                Portuguese -> "Português"
                Russian -> "Русский"
                Spanish -> "Español"
                Swedish -> "Svenska"
                Turkish -> "Türkçe"
            }
        }
    }

    fun Language.value(): String {
        return when(this) {
            Language.System -> Locale.getDefault().language
            Language.Arabic -> "ar"
            Language.English -> "en"
            Language.German -> "de"
            Language.Spanish -> "es"
            Language.French -> "fr"
            Language.Italian -> "it"
            Language.Swedish -> "se"
            Language.Turkish -> "tr"
            Language.Greek -> "gr"
            Language.Chinese -> "cn"
            Language.Persian -> "fa"
            Language.Portuguese -> "pt"
            Language.Korean -> "ko"
            Language.Indonesian -> "id"
            Language.Russian -> "ru"
            Language.Croatian -> "hr"
            Language.Estonian -> "et"
            Language.Dutch -> "nl"
        }
    }

    enum class Gravity: EnumOption {
        Left,
        Center,
        Right;

        @Composable
        override fun string(): String {
            return when(this) {
                Left -> stringResource(R.string.left)
                Center -> stringResource(R.string.center)
                Right -> stringResource(R.string.right)
            }
        }

        @SuppressLint("RtlHardcoded")
        fun value(): Int {
            return when(this) {
                Left -> android.view.Gravity.LEFT
                Center -> android.view.Gravity.CENTER
                Right -> android.view.Gravity.RIGHT
            }
        }
    }

    enum class Action: EnumOption {
        Disabled,
        OpenApp,
        LockScreen,
        ShowAppList,
        ShowNotification;

        @Composable
        override fun string(): String {
            return when(this) {
                OpenApp -> stringResource(R.string.open_app)
                LockScreen -> stringResource(R.string.lock_screen)
                ShowNotification -> stringResource(R.string.show_notifications)
                ShowAppList -> stringResource(R.string.show_app_list)
                Disabled -> stringResource(R.string.disabled)
            }
        }
    }

    enum class Theme: EnumOption {
        System,
        Dark,
        Light;

        @Composable
        override fun string(): String {
            return when(this) {
                System -> stringResource(R.string.lang_system)
                Dark -> stringResource(R.string.dark)
                Light -> stringResource(R.string.light)
            }
        }
    }
}
