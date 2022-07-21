package cz.jannejezchleba.appliftingspacex.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Padding(
    val XS: Dp = 4.dp,
    val S: Dp = 8.dp,
    val M: Dp = 16.dp,
    val L: Dp = 24.dp,
    val XL: Dp = 48.dp,
)

internal val LocalPaddings = staticCompositionLocalOf { Padding() }