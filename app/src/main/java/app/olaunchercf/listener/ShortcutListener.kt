package app.olaunchercf.listener

import android.content.Intent
import android.content.pm.LauncherApps.EXTRA_PIN_ITEM_REQUEST
import android.content.pm.LauncherApps.PinItemRequest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import app.olaunchercf.MainActivity
import app.olaunchercf.data.AppModel
import app.olaunchercf.data.Prefs

class ShortcutListener: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Currently only Android versions higher than O are supported
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val request = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(EXTRA_PIN_ITEM_REQUEST, PinItemRequest::class.java)
            } else {
                intent.getParcelableExtra(EXTRA_PIN_ITEM_REQUEST)
            }

            request?.let { it ->
                val shortcut = it.shortcutInfo

                shortcut?.let {
                    val name = "AAAA" // shortcut.shortLabel.toString()
                    val id = shortcut.id
                    val pack = shortcut.`package`
                    val activity = shortcut.activity!!.packageName
                    val user = shortcut.userHandle

                    Log.d("shortcut", "$name, $id, $pack, $activity, $user")

                    val model = AppModel(
                        appLabel = name,
                        appPackage = pack,
                        user = user,
                        appAlias = id,
                        key = null,
                        appActivityName = activity,
                    )

                    Prefs(applicationContext).shortcut = model
                    Log.d("shortcuts", "Stored")

                    /*val launcher = application.applicationContext.getSystemService(Context.LAUNCHER_APPS_SERVICE) as LauncherApps
                    if (launcher.hasShortcutHostPermission()) {
                        //launcher.startShortcut(it, null, null)
                        launcher.startShortcut(pack, id, null, null, user)
                    }*/


                    // Go back to the home screen
                    val homeIntent = Intent()
                    homeIntent.setClass(this, MainActivity::class.java)
                    homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(homeIntent)

                    //MainViewModel(application).selectedApp(model,Constants.AppDrawerFlag.LaunchApp)

                }
            }

        }
    }
}