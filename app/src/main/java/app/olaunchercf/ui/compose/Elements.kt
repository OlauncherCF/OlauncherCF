package app.olaunchercf.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import app.olaunchercf.R

object Elements {

    @Composable
    fun SimpleButton(
        text: String,
        onClick: () -> Unit,
    ) {
        TextButton(
            onClick = onClick,
        ) {
            Text(
                text = text,
                style = SettingsTheme.typography.item,
            )
        }
    }

    @Composable
    fun NumberSelector(
        number: MutableState<Int>,
        min: Int,
        max: Int,
        onCommit: (Int) -> Unit
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(SettingsTheme.color.selector, SettingsTheme.shapes.settings)
                .fillMaxWidth()
        ) {
            val (plus, minus, text, button) = createRefs()
            TextButton(
                onClick = { if (number.value < max) number.value += 1 },
                modifier = Modifier.constrainAs(plus) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(text.start)
                },
            ) {
                Text("+", style = SettingsTheme.typography.button)
            }
            Text(
                text = number.value.toString(),
                modifier = Modifier
                    .fillMaxHeight()
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(plus.end)
                        end.linkTo(minus.start)
                    },
                style = SettingsTheme.typography.item,
            )
            TextButton(
                onClick = { if (number.value > min) number.value -= 1 },
                modifier = Modifier.constrainAs(minus) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(text.end)
                    end.linkTo(button.start)
                },
            ) {
                Text("-", style = SettingsTheme.typography.button)
            }
            TextButton(
                onClick = { onCommit(number.value) },
                modifier = Modifier.constrainAs(button) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(minus.end)
                    end.linkTo(parent.end)
                },
            ) {
                Text(stringResource(R.string.commit), style = SettingsTheme.typography.button)
            }
        }
    }

    @Composable
    fun <T> ListSelector(options: Array<T>, onSelect: (T) -> Unit) {
        Box(
            modifier = Modifier
                .background(SettingsTheme.color.selector, SettingsTheme.shapes.settings)
                .fillMaxWidth()
        ) {
            LazyRow(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                for (opt in options) {
                    item {
                        TextButton(
                            onClick = { onSelect(opt) },
                        ) {
                            Text(
                                text = opt.toString(),
                                style = SettingsTheme.typography.button
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Element(
        title: String,
        onClick: () -> Unit,
        buttonText: String,
    ) {
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
                    /*overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    textAlign = TextAlign.Left,*/
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