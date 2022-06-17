import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.olaunchercf.style.primaryGreen
import app.olaunchercf.style.textColorLight

@Immutable
data class SettingsTypography(
    val item: TextStyle,
    val title: TextStyle,
    val button: TextStyle,
)

@Immutable
data class SettingsShapes(
    val component: Shape,
    val surface: Shape
)

val LocalSettingsTypography = staticCompositionLocalOf {
    SettingsTypography(
        item = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            color = textColorLight,
            textAlign = TextAlign.Start
        ),
        button = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            color = textColorLight,
            textAlign = TextAlign.End
        ),
        title = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light,
            fontSize = 20.sp,
            color = textColorLight,
        ),
    )
}
val LocalSettingsShapes = staticCompositionLocalOf {
    SettingsShapes(
        component = RoundedCornerShape(ZeroCornerSize),
        surface = RoundedCornerShape(ZeroCornerSize)
    )
}

@Composable
fun SettingsTheme(
    content: @Composable () -> Unit
) {
    /*val settingsTypography = SettingsTypography(
        item = TextStyle(fontSize = 16.sp),
        title = TextStyle(fontSize = 32.sp)
    )*/
    val settingsShapes = SettingsShapes(
        component = RoundedCornerShape(percent = 50),
        surface = RoundedCornerShape(size = 40.dp)
    )
    CompositionLocalProvider {
        MaterialTheme(
            content = content
        )
    }
}

// Use with eg. ReplacementTheme.typography.body
object SettingsTheme {
    val typography: SettingsTypography
        @Composable
        get() = LocalSettingsTypography.current
    val shapes: SettingsShapes
        @Composable
        get() = LocalSettingsShapes.current
}
