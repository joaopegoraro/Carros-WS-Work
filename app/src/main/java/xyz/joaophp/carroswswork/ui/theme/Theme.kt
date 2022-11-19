package xyz.joaophp.carroswswork.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = wsBlue,
    primaryVariant = wsLightBlue,
    secondary = wsYellow,
    secondaryVariant = wsYellowVariant,
    onBackground = wsGrey,
    onSurface = wsWhite,
    background = wsDarkGrey,
    surface = wsDarkGrey,
    onPrimary = wsWhite,
    onSecondary = wsDarkGrey,
    error = wsPink,
    onError = wsWhite
)

private val LightColorPalette = lightColors(
    primary = wsBlue,
    primaryVariant = wsLightBlue,
    secondary = wsYellow,
    secondaryVariant = wsYellowVariant,
    background = wsGrey,
    surface = wsWhite,
    onBackground = wsDarkGrey,
    onSurface = wsDarkGrey,
    onPrimary = wsWhite,
    onSecondary = wsDarkGrey,
    error = wsPink,
    onError = wsWhite
)

@Composable
fun CarrosWSWorkTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = {
            val systemUiController = rememberSystemUiController()
            val statusBarColor = MaterialTheme.colors.primary
            LaunchedEffect(true) {
                systemUiController.setSystemBarsColor(
                    color = statusBarColor
                )
            }
            content()
        }
    )
}