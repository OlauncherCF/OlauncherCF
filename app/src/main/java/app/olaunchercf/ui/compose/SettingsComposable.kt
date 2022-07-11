package app.olaunchercf.ui.compose

import SettingsTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import app.olaunchercf.R
import app.olaunchercf.data.EnumOption
import app.olaunchercf.style.CORNER_RADIUS
import app.olaunchercf.ui.compose.Selector.ListSelector
import app.olaunchercf.ui.compose.Selector.NumberSelector
import app.olaunchercf.ui.compose.Selector.ColorSelector

object SettingsComposable {
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
    fun ToggleItem(
        title: String,
        state: MutableState<Boolean>,
        onClick: (Boolean) -> Unit,
        onToggle: () -> Unit
    ) {
        val buttonText = if (state.value) stringResource(R.string.on) else stringResource(R.string.off)
        Entry(
            title = title,
            onClick = {
                onClick(false)
                state.value = !state.value
                onToggle()
            },
            buttonText = buttonText,
            open = remember { mutableStateOf(false) }
        ) {

        }
    }

    @Composable
    fun <T: EnumOption> ListItem(
        title: String,
        currentSelection: MutableState<T>,
        values: Array<T>,
        open: MutableState<Boolean>,
        onClick: (Boolean) -> Unit,
        onSelectItem: (T) -> Unit,
    ) {
        Entry(
            title = title,
            onClick = { onClick(true) },
            buttonText = currentSelection.value.string(),
            open = open,
        ) {
            Box(
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures {
                            onClick(false)
                        }
                    }
                    .onFocusEvent {
                        if (it.isFocused) {
                            onClick(false)
                        }
                    }
            ) {
                ListSelector(values) { i ->
                    onClick(false)
                    currentSelection.value = i
                    onSelectItem(i)
                }
            }
        }
    }

    @Composable
    fun NumberItem(
        title: String,
        currentSelection: MutableState<Int>,
        min: Int,
        max: Int,
        open: MutableState<Boolean>,
        onClick: (Boolean) -> Unit,
        onSelectNumber: (Int) -> Unit
    ) {
        Entry (
            title = title,
            buttonText = currentSelection.value.toString(),
            open = open,
            onClick = { onClick(true) }
        ) {
            NumberSelector(
                number = currentSelection,
                min = min,
                max = max,
            ) { i ->
                onClick(false)
                currentSelection.value = i
                onSelectNumber(i)
            }
        }
    }

    @Composable
    fun ColorItem(
        title: String,
        buttonText: String,
        colors: Array<Color>,
        open: MutableState<Boolean>,
        currentSelection: MutableState<Color>,
        onSelectColor: (Color) -> Unit,
        onClick: (Boolean) -> Unit,
    ) {
        Entry(
            title = title,
            buttonText = buttonText,
            open = open,
            onClick = { onClick(true) },
        ) {
            ColorSelector(
                colors = colors,
                onSelectColor = { color ->
                    onClick(false)
                    currentSelection.value = color
                    onSelectColor(color)
                }
            )
        }
    }

    @Composable
    fun AppItem(
        title: String,
        currentSelection: MutableState<String>,
        onClick: () -> Unit,
    ) {
        Entry(
            title = title,
            buttonText = currentSelection.value,
            onClick = onClick,
            open = remember { mutableStateOf(false) },
        ) {

        }
    }

    @Composable
    internal fun Entry(
        title: String,
        buttonText: String,
        open: MutableState<Boolean>,
        onClick: () -> Unit,
        openView: @Composable () -> Unit,
    ) {
        if (open.value) {
            openView()
        } else {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (text, button) = createRefs()
                Box(
                    modifier = Modifier
                        .constrainAs(text) {
                            start.linkTo(parent.start)
                            end.linkTo(button.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                        },
                ) {
                    Text(
                        title,
                        style = SettingsTheme.typography.item,
                    modifier = Modifier.align(Alignment.CenterStart)
                    )
                }

                TextButton(
                    onClick = onClick,
                    modifier = Modifier.constrainAs(button) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                ) {
                    Text(
                        text = buttonText,
                        style = SettingsTheme.typography.button
                    )
                }
            }
        }
    }
}