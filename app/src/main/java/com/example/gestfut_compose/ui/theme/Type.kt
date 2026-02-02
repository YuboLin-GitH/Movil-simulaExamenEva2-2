package com.example.gestfut_compose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.gestfut_compose.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )



)
//Defino el estilo del texto del TopBar
val ToolbarTitleStyle = TextStyle(
    fontFamily = FontFamily(Font(R.font.aspirenarrow)),
    fontWeight = FontWeight.Bold,
    fontSize = 30.sp,
    color = PrimaryLight
)