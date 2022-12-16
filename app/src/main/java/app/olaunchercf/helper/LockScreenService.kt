package app.olaunchercf.helper

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.os.Build
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import java.lang.ref.WeakReference

class LockScreenService : AccessibilityService() {

    override fun onServiceConnected() {
        instance = WeakReference(this)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        instance = WeakReference(null)
        return super.onUnbind(intent)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun lockScreen(): Boolean {
        return performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() { }

    companion object {
        private var instance: WeakReference<LockScreenService> = WeakReference(null)

        fun instance(): LockScreenService? {
            return instance.get()
        }
    }
}