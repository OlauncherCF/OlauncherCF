package app.olaunchercf.ui.compose

import SettingsTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import app.olaunchercf.R
import app.olaunchercf.style.CORNER_RADIUS
import app.olaunchercf.ui.compose.Elements.NumberSelector
import app.olaunchercf.ui.compose.Elements.Element
import app.olaunchercf.ui.compose.Elements.ListSelector

object SettingsComposable {

    @Composable
    fun AppSelector(
        title: String,
        currentSelection: MutableState<String>,
        onClick: () -> Unit,
    ) {
        Element(
            title = title,
            onClick = onClick,
            buttonText = currentSelection.value
        )
    }

    @Composable
    fun SettingsArea (
        title: String,
        items: Array<@Composable (MutableState<Boolean>, (Boolean) -> Unit ) -> Unit>
    ) {
        val selected = remember { mutableStateOf(-1) }
        Column(
            modifier = Modifier
                .padding(12.dp, 12.dp, 12.dp, 0.dp)
                .background(SettingsTheme.color.settings, SettingsTheme.shapes.settings)
                .border(
                    0.5.dp,
                    colorResource(R.color.blackInverseTrans50),
                    RoundedCornerShape(CORNER_RADIUS),
                )
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            SettingsTitle(text = title)
            items.forEachIndexed { i, item ->
                item(mutableStateOf(i == selected.value)) { b -> selected.value = if (b) i else -1 }
            }
        }
    }

    @Composable
    fun SettingsTitle(text: String) {
        Text(
            text = text,
            style = SettingsTheme.typography.title,
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 12.dp)
        )
    }

    @Composable
    fun SettingsToggle(
        title: String,
        state: MutableState<Boolean>,
        onChange: (Boolean) -> Unit,
        onToggle: () -> Unit
    ) {
        val buttonText = if (state.value) "On" else "Off"
        Element(
            title = title,
            onClick = {
                onChange(false)
                state.value = !state.value
                onToggle()
            },
            buttonText = buttonText
        )
    }

    @Composable
    fun TwoButtons(
        title1: String,
        onClick1: () -> Unit,
        title2: String,
        onClick2: () -> Unit,
    ) {
        Row {
            Elements.SimpleButton(
                text = title1,
                onClick = onClick1
            )
            Elements.SimpleButton(
                text = title2,
                onClick = onClick2
            )
        }
    }

    @Composable
    fun <T> SettingsItem(
        title: String,
        currentSelection: MutableState<T>,
        values: Array<T>,
        open: MutableState<Boolean>,
        onChange: (Boolean) -> Unit,
        onSelect: (T) -> Unit,
    ) {
        if (open.value) {
            Box(
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures {
                            onChange(false)
                        }
                    }
                    .onFocusEvent {
                        if (it.isFocused) {
                            onChange(false)
                        }
                    }
            ) {
                ListSelector(values) { i ->
                    onChange(false)
                    currentSelection.value = i
                    onSelect(i)
                }
            }
        } else {
            Element(
                title = title,
                onClick = { onChange(true) },
                buttonText = currentSelection.value.toString()
            )
        }
    }

    @Composable
    fun SettingsNumberItem(
        title: String,
        currentSelection: MutableState<Int>,
        min: Int,
        max: Int,
        open: MutableState<Boolean>,
        onChange: (Boolean) -> Unit,
        onSelect: (Int) -> Unit
    ) {
        if (open.value) {
            NumberSelector(
                number = currentSelection,
                min = min,
                max = max,
            ) { i ->
                onChange(false)
                currentSelection.value = i
                onSelect(i)
            }
        } else {
            Element(
                title = title,
                onClick = { onChange(true) },
                buttonText = currentSelection.value.toString()
            )
        }
    }
}