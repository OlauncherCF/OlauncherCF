package app.olaunchercf.data

object Constants {
    const val WALL_TYPE_LIGHT = "light"
    const val WALL_TYPE_DARK = "dark"

//    const val THEME_MODE_DARK = 0
//    const val THEME_MODE_LIGHT = 1
//    const val THEME_MODE_SYSTEM = 2

    const val FLAG_LAUNCH_APP = 100
    const val FLAG_HIDDEN_APPS = 101

    const val FLAG_SET_HOME_APP_1 = 1
    const val FLAG_SET_HOME_APP_2 = 2
    const val FLAG_SET_HOME_APP_3 = 3
    const val FLAG_SET_HOME_APP_4 = 4
    const val FLAG_SET_HOME_APP_5 = 5
    const val FLAG_SET_HOME_APP_6 = 6
    const val FLAG_SET_HOME_APP_7 = 7
    const val FLAG_SET_HOME_APP_8 = 8

    const val FLAG_SET_SWIPE_LEFT_APP = 11
    const val FLAG_SET_SWIPE_RIGHT_APP = 12

    const val FLAG_SET_CLICK_CLOCK_APP = 13
    const val FLAG_SET_CLICK_DATE_APP = 14

    const val REQUEST_CODE_ENABLE_ADMIN = 666

    const val TRIPLE_TAP_DELAY_MS = 300
    const val LONG_PRESS_DELAY_MS = 500

    const val LANG_EN = 1000//"en"
    const val LANG_DE = 1001//"de"
    const val LANG_ES = 1002//"es"
    const val LANG_FR = 1003//"fr"
    const val LANG_IT = 1004//"it"
    const val LANG_SE = 1005//"se"
    const val LANG_TR = 1006//"tr"

    const val TEXT_SIZE_HUGE = 30f
    const val TEXT_SIZE_NORMAL = 22f
    const val TEXT_SIZE_SMALL = 16f

    const val URL_ABOUT_OLAUNCHER = "https://tanujnotes.notion.site/Olauncher-Minimal-AF-4843e398b05a455bb521b0665b26fbcd"
    const val URL_OLAUNCHER_PRIVACY = "https://tanujnotes.notion.site/Olauncher-Privacy-Policy-dd6ac5101ddd4b3da9d27057889d44ab"
    const val URL_PUBLIC_ROADMAP = "https://tanujnotes.notion.site/Olauncher-Roadmap-1522aa8182424415b5b89bed923b0ccd"
    const val URL_OLAUNCHER_GITHUB = "https://www.github.com/tanujnotes/Olauncher"
    const val URL_OLAUNCHER_PLAY_STORE = "https://play.google.com/store/apps/details?id=app.olauncher"
    const val URL_TWITTER_TANUJ = "https://twitter.com/tanujnotes"
    const val URL_INSTA_OLAUNCHER = "https://instagram.com/olauncherapp"
    const val URL_AFFILIATE = "https://amzn.to/3uftwFW"

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