package cz.jannejezchleba.appliftingspacex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Black900,
    primaryVariant = Gray,
    secondary = Color.White,
    onPrimary = Color.White
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Gray,
    secondary = Black900,
    onPrimary = Black900
)

@Composable
fun AppliftingSpaceXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalPaddings provides Padding()
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object CustomMaterialTheme {
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes

    val padding: Padding
        @Composable
        get() = LocalPaddings.current
}