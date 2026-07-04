package io.github.jqssun.airplay.ui.theme

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

object MiCastTheme {
    const val SUNSET = "sunset"
    const val PLUM = "plum"
    private const val PREFS = "micast_theme"
    private const val KEY = "theme"

    val current = mutableStateOf(SUNSET)

    fun load(context: Context) {
        current.value = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getString(KEY, SUNSET) ?: SUNSET
    }

    fun set(context: Context, name: String) {
        current.value = name
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit().putString(KEY, name).apply()
    }
}

private val CandyPink = Color(0xFFFF6FAE)
private val BerryInk = Color(0xFF4B1528)
private val Raspberry = Color(0xFFD6336C)

private val SunsetScheme = lightColorScheme(
    primary = CandyPink,
    onPrimary = BerryInk,
    secondary = Raspberry,
    onSecondary = Color(0xFFFFF6EF),
    tertiary = Color(0xFF2F9E6E),
    onTertiary = Color(0xFFFFF6EF),
    background = Color(0xFFFFE9DA),
    onBackground = Color(0xFF45231A),
    surface = Color(0xFFFFDCC5),
    onSurface = Color(0xFF543127),
    surfaceVariant = Color(0xFFFFF6EF),
    onSurfaceVariant = Color(0xFF815A48),
    outline = Color(0xFF9A6F5F)
)

private val PlumScheme = darkColorScheme(
    primary = CandyPink,
    onPrimary = BerryInk,
    secondary = Color(0xFFFFC7DE),
    onSecondary = BerryInk,
    tertiary = Color(0xFF7FE0B0),
    onTertiary = BerryInk,
    background = Color(0xFF241722),
    onBackground = Color(0xFFFFF6FA),
    surface = Color(0xFF2E1C2A),
    onSurface = Color(0xFFF3DCE8),
    surfaceVariant = Color(0xFF3B2233),
    onSurfaceVariant = Color(0xFFFFC7DE),
    outline = Color(0xFFC9A9BD)
)

@Composable
fun AirPlayTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val ctx = LocalContext.current
    remember { MiCastTheme.load(ctx) }
    val name by MiCastTheme.current
    val colorScheme = if (name == MiCastTheme.PLUM) PlumScheme else SunsetScheme
    MaterialTheme(colorScheme = colorScheme, content = content)
}
