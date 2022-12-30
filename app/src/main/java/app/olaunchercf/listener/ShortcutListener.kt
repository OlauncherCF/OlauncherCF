package app.olaunchercf.listener

import android.content.ComponentName
import android.content.Context
import android.content.pm.LauncherApps
import android.content.pm.LauncherApps.EXTRA_PIN_ITEM_REQUEST
import android.content.pm.LauncherApps.PinItemRequest
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.os.UserHandle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import app.olaunchercf.MainViewModel
import app.olaunchercf.data.AppModel
import app.olaunchercf.data.Constants
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

            request?.let {
                val shortcut = it.shortcutInfo

                shortcut?.let {
                    val name = "AAAAAAAAA" // shortcut.shortLabel.toString()
                    // val id = shortcut.id
                    val pack = shortcut.`package`
                    val activity = shortcut.activity!!.packageName
                    val user = shortcut.userHandle

                    val model = AppModel(
                        appLabel = name,
                        appPackage = pack,
                        user = user,
                        appAlias = "",
                        key = null,
                        appActivityName = activity,
                    )

                    Prefs(applicationContext).shortcut = model
                    Log.d("shortcuts", "Stored")

                    //val launcher = application.applicationContext.getSystemService(Context.LAUNCHER_APPS_SERVICE) as LauncherApps
                    //launcher.startMainActivity(ComponentName(pack, name), user, null, null)

                    //MainViewModel(application).selectedApp(model,Constants.AppDrawerFlag.LaunchApp)

                }
            }

        }
    }
}