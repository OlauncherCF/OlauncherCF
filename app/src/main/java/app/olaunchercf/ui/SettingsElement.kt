package app.olaunchercf.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class SettingsElement(
    val title: String,
    private val onValueClickAction: (Int) -> Unit,
    val optValues: Array<Int>,
    val optValueNames: Array<String> = arrayOf(),
) {
    var open = mutableStateOf(false)
    var selected: MutableState<String> = mutableStateOf(this.names()[0])

    fun onClick() {
        this.open.value = true
    }

    fun onValueClick(i: T, name: String) {
        this.open.value = false
        this.onValueClickAction(i)
        this.selected.value = name
    }

    fun names(): Array<String> {
        return this.optValueNames.ifEmpty {
            this.optValues.map { it.toString() }.toTypedArray()
        }
    }
}