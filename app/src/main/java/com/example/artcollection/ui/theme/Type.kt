package com.example.artcollection.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.artcollection.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val TimesNRFont = FontFamily(
    Font(R.font.timesnewromanpsmt)
)
val IdealistSansFont = FontFamily(
    Font(R.font.idealist_sans)
)

val FiraSansBoldFont = FontFamily(
    Font(R.font.fira_sans_bold)
)

val DetailGrayTextStyle = TextStyle(
    fontFamily = IdealistSansFont,
    fontSize = 18.sp,
    color = GrayTextColor
)

val DetailTextStyle = TextStyle(
    fontFamily = IdealistSansFont,
    fontSize = 18.sp
)

val DetailBoldTextStyle = TextStyle(
    fontFamily = FiraSansBoldFont,
    fontSize = 18.sp
)