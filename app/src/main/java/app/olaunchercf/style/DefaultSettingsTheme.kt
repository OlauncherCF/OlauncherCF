package app.olaunchercf.style

import ColorScheme
import DefaultTextStyle
import LocalReplacementColor
import LocalReplacementShapes
import LocalReplacementTypography
import ReplacementColor
import ReplacementShapes
import ReplacementTypography
import android.graphics.Color.parseColor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import app.olaunchercf.R


class SoftColors: ColorScheme() {
    override val settingsBackgroundDark = Color(parseColor("#28211B"))
    override val settingsBackgroundLight = Color(parseColor("#DAD0C6"))
    override val textDark = settingsBackgroundDark
    override val textLight = settingsBackgroundLight
    override val selectionBackgroundDark = Color(parseColor("#614A45"))
    override val selectionBackgroundLight = Color(parseColor("#B39C7D"))
    override val textDisabled = textGray
}

@Composable
fun SettingsTheme(
    isDark: Boolean,
    colorScheme: ColorScheme,
    content: @Composable () -> Unit
) {
    val replacementTypography = ReplacementTypography(
        body = DefaultTextStyle(fontSize = 16.sp, isDark = isDark, colorDark = colorScheme.textDark, colorLight = colorScheme.textLight),
        title = DefaultTextStyle(fontSize = 32.sp, isDark = isDark, colorDark = colorScheme.textDark, colorLight = colorScheme.textLight),
        item = DefaultTextStyle(fontSize = 16.sp, isDark = isDark, colorDark = colorScheme.textDark, colorLight = colorScheme.textLight),
        button = DefaultTextStyle(fontSize = 16.sp, bold = true, isDark = isDark, colorDark = colorScheme.textDark, colorLight = colorScheme.textLight),
        buttonDisabled = DefaultTextStyle(fontSize = 16.sp, bold = true, isDark = isDark, colorDark = colorScheme.textDisabled, colorLight = colorScheme.textDisabled),
    )
    val replacementShapes = ReplacementShapes(
        settings = RoundedCornerShape(CORNER_RADIUS),
    )
    val replacementColor = ReplacementColor(
        // settingsBackground = colorResource( if (isDark) R.color.blackTrans50 else R.color.blackInverseTrans50 ),
        // selectionBackground = colorResource( if (isDark) R.color.blackTrans80 else R.color.blackInverseTrans80 ),
        settingsBackground = if (isDark) colorScheme.settingsBackgroundDark else colorScheme.settingsBackgroundLight,
        selectionBackground = if (isDark) colorScheme.selectionBackgroundDark else colorScheme.selectionBackgroundLight,
    )
    CompositionLocalProvider(
        LocalReplacementTypography provides replacementTypography,
        LocalReplacementShapes provides replacementShapes,
        LocalReplacementColor provides replacementColor,
        content = content
    )
}
// Use with eg. ReplacementTheme.typography.body
object SettingsTheme {
    val typography: ReplacementTypography
        @Composable
        get() = LocalReplacementTypography.current

    val shapes: ReplacementShapes
        @Composable
        get() = LocalReplacementShapes.current

    val color: ReplacementColor
        @Composable
        get() = LocalReplacementColor.current
}
