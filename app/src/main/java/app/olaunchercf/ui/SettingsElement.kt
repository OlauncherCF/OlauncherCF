package app.olaunchercf.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class SettingsElement {
    val text = "Auto Show Keyboard"
    var open = mutableStateOf(false)
    val opt = "on"

    fun onClick() {
        Log.d("compose", "clicked")
        this.open.value = true
    }
}