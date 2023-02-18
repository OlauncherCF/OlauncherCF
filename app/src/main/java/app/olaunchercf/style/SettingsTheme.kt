import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import app.olaunchercf.style.textDark
import app.olaunchercf.style.textLight

// Definition of element classes
@Immutable
data class ReplacementTypography(
    val title: TextStyle,
    val body: TextStyle,
    val item: TextStyle,
    val button: TextStyle,
    val buttonDisabled: TextStyle,
)

@Immutable
data class ReplacementShapes(
    val settings: Shape,
)

@Immutable
data class ReplacementColor(
    val settingsBackground: Color,
    val selectionBackground: Color,
)

val LocalReplacementTypography = staticCompositionLocalOf {
    ReplacementTypography(
        title = TextStyle.Default,
        body = TextStyle.Default,
        item = TextStyle.Default,
        button = TextStyle.Default,
        buttonDisabled = TextStyle.Default,
    )
}
val LocalReplacementShapes = staticCompositionLocalOf {
    ReplacementShapes(
        settings = RoundedCornerShape(ZeroCornerSize)
    )
}
val LocalReplacementColor = staticCompositionLocalOf {
    ReplacementColor(
        settingsBackground = Color.Unspecified,
        selectionBackground = Color.Unspecified,
    )
}

@Composable
fun DefaultTextStyle(
    fontSize: TextUnit = 16.sp,
    bold: Boolean = false,
    isDark: Boolean = false,
    colorLight: Color = textLight,
    colorDark: Color = textDark,
): TextStyle {
    return TextStyle(
        fontSize = fontSize,
        fontFamily = FontFamily.SansSerif,
        fontWeight = if (bold) FontWeight.Bold else FontWeight.Light,
        color = if (isDark) colorLight else colorDark, // this is not a mistake
    )
}

abstract class ColorScheme {
    abstract val settingsBackgroundDark: Color
    abstract val settingsBackgroundLight: Color
    abstract val textDark: Color
    abstract val textLight: Color
    abstract val textDisabled: Color
    abstract val selectionBackgroundDark: Color
    abstract val selectionBackgroundLight: Color
}
