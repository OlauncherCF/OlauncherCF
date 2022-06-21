package app.olaunchercf.data

object Constants {

//    const val THEME_MODE_DARK = 0
//    const val THEME_MODE_LIGHT = 1
//    const val THEME_MODE_SYSTEM = 2

    const val FLAG_LAUNCH_APP = 100
    const val FLAG_HIDDEN_APPS = 101

    const val FLAG_SET_HOME_APP = 1

    const val FLAG_SET_SWIPE_LEFT_APP = 11
    const val FLAG_SET_SWIPE_RIGHT_APP = 12

    const val FLAG_SET_CLICK_CLOCK_APP = 13
    const val FLAG_SET_CLICK_DATE_APP = 14

    const val REQUEST_CODE_ENABLE_ADMIN = 666

    const val TRIPLE_TAP_DELAY_MS = 300
    const val LONG_PRESS_DELAY_MS = 500

    const val MAX_HOME_APPS = 15

    /* const val LANG_EN = 1000//"en"
     * const val LANG_DE = 1001//"de"
     * const val LANG_ES = 1002//"es"
     * const val LANG_FR = 1003//"fr"
     * const val LANG_IT = 1004//"it"
     * const val LANG_SE = 1005//"se"
     * const val LANG_TR = 1006//"tr" */

    enum class Language {
        System,
        English,
        German,
        Spanish,
        French,
        Italian,
        Swedish,
        Turkish
    }

    enum class Gravity {
        Left,
        Center,
        Right
    }

    enum class Theme {
        System,
        Dark,
        Light
    }
}
