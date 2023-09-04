package com.compose.platform.common.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(
    val space0dp: Dp = 0.dp,
    val space2dp: Dp = 2.dp,
    val space4dp: Dp = 4.dp,
    val space6dp: Dp = 6.dp,
    val space7dp: Dp = 7.dp,
    val space8dp: Dp = 8.dp,
    val space10dp: Dp = 10.dp,
    val space12dp: Dp = 12.dp,
    val space16dp: Dp = 16.dp,
    val space18dp: Dp = 18.dp,
    val space20dp: Dp = 20.dp,
    val space24dp: Dp = 24.dp,
    val space32dp: Dp = 32.dp,
    val space64dp: Dp = 64.dp
)

val LocalSpacing = compositionLocalOf { Dimensions() }
