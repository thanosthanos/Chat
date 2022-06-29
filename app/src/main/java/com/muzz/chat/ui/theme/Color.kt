package com.muzz.chat.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Red200 = Color(0xFFdc1068)
val Red500 = Color(0xFFdc1068)
val Red700 = Color(0xFFe048b5)
val Teal200 = Color(0xFF03DAC5)

@get:Composable
val Colors.OtherMessageColor: Color
    get() = if (isLight) Color(0xFFd2d3d9) else Color(0xFFd2d3d9)
