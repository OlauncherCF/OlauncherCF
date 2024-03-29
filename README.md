<h1 align="center">Minimal and clutter-free Android launcher</h1>

<img src="https://github.com/OlauncherCF/OlauncherCF/assets/57965027/10bc887a-2e68-4fda-88a0-b12ce4b2e69b" width="1000">

[<img src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png"
    alt="Get it on F-Droid"
    height="80">](https://f-droid.org/packages/app.olaunchercf/)
	<!-- <a href="https://github.com/OlauncherCF/OlauncherCF/releases/" target="_blank">
	<img src="https://github.com/OlauncherCF/OlauncherCF/blob/67fa100d3f3c76111e75007cedf8b0e568aa2a42/art/get-it-on-github.png" alt="Get it on GitHub" height="80"/> Useful when relsease will be automatically generated by github -->
</a>

- This app is available on [F-Droid](https://f-droid.org/packages/app.olaunchercf/) <!-- & [Github](https://github.com/OlauncherCF/OlauncherCF/releases/) Useful when relsease will be automatically generated by github -->
- The latest stable version is on the [`main`](https://github.com/OlauncherCF/OlauncherCF/tree/main) branch. You can clone it and build the app yourself.
	<!-- - A github action should build an apk for every [release](https://github.com/OlauncherCF/OlauncherCF/releases). Useful when relsease will be automatically generated by github -->
- The **original app** is also available on [Play Store](https://play.google.com/store/apps/details?id=app.olauncher), [F-Droid](https://f-droid.org/fr/packages/app.olauncher/) & [Github](https://github.com/tanujnotes/Olauncher).

## Forked with extra features

- Originally based on [Olauncher](https://github.com/tanujnotes/Olauncher)
- Removed clutter, like ads and links
- You can rename apps in the app-drawer _(Renaming apps on the home screen is already supported. Just long-click on an app on the home screen and start typing)_
- We have added a lot more options for gestures on the home screen:
    - Gestures are now:
        - Swiping up, down, left, right
        - Clicking on the clock
        - Clicking on the Date
    - Possible actions now include:
        - Open specified app
        - Locking the screen
        - Opening the notification drawer
        - Opening the quick settings
- You can also position the clock independently of the home apps
- Change alignment of apps in app-drawer
- Change font size
- Removed internet permission. You never know what an app developer wants to know about you.

## Contribute

- This app is basically feature complete. However, if you find any bugs, please search for existing issues, and if you don't find any feel free to open a new one.

## Translations:

- A lot of people have translated the app to the following languages. Many thanks to you ❤️
  - Arabic
  - Chinese
  - Croatian
  - Dutch
  - English
  - Estonian
  - French
  - German
  - Greek
  - Indonesian
  - Italian
  - Japanese
  - Korean
  - Lithuanian
  - Persian
  - Portuguese (European)
  - Russian
  - Spanish
  - Swedish
  - Thai
  - Turkish

## License

**Olauncher CF is under open source GPL3 license, meaning you can use, study, change and share it at will.**
Copyleft ensures it stays that way. From the full source, anyone can build, fork and use as you wish

* Olauncher CF does not have network access.
* Olauncher CF does not collect or transmit any data in any way whatsoever.

## Permissions

Olauncher CF uses the following permissions:

- `android.permission.EXPAND_STATUS_BAR`
	- Allows an application to expand or collapse the status bar.
- `android.permission.QUERY_ALL_PACKAGES`
	- Allows query of any normal app on the device, regardless of manifest declarations. Used to show the apps list.
- `android.alarm.permission.SET_ALARM`
	- Allows an application to broadcast an Intent to set an alarm for the user. Used to open the default alarm app if no other clock app is set in the settings.
- `android.permission.REQUEST_DELETE_PACKAGES`
	- Required for issuing the request to remove packages. This does not allow the app to remove apps directly; this only gives the permission to issue the request.

## Support me personally

<a href="https://www.buymeacoffee.com/jooooscha" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-yellow.png" alt="Buy Me A Coffee" style="height: 60px !important;width: 217px !important;" ></a>
