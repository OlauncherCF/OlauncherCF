package app.olaunchercf.data

import android.os.UserHandle
import java.text.CollationKey

data class AppModel(
        val appLabel: String,
        val key: CollationKey?,
        val appPackage: String,
        val appActivityName: String,
        val user: UserHandle,
        var appAlias: String
) : Comparable<AppModel> {
    override fun compareTo(other: AppModel): Int = when {
        key != null && other.key != null -> key.compareTo(other.key)
        else -> appLabel.compareTo(other.appLabel, true)
    }

    var name: String = appAlias.ifEmpty { appLabel }
}
