package com.example.gobeyond

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gobeyond.ui.theme.GoBeyondTheme
import com.example.gobeyond.ui.main.MainScreen
import androidx.navigation.compose.rememberNavController
import com.example.gobeyond.navigation.AppNavGraph
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat

import androidx.lifecycle.lifecycleScope
import com.example.gobeyond.ui.data.AppDatabaseProvider
import com.example.gobeyond.ui.data.CountryRepository
import com.example.gobeyond.ui.data.DestinationRepository
import com.example.gobeyond.ui.explore.CountryListScreen
import com.example.gobeyond.ui.explore.CountryViewModel
import com.example.gobeyond.ui.model.Country
import com.example.gobeyond.ui.model.Destination
import kotlinx.coroutines.launch

import org.maplibre.android.MapLibre
import org.maplibre.android.WellKnownTileServer

//import org.osmdroid.config.Configuration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        val ctx = applicationContext

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = AppDatabaseProvider.createDatabase(applicationContext)

        lifecycleScope.launch {
            val repository = DestinationRepository(
                db.destinationDao(),
                applicationContext
            )

            repository.seedDestinationsIfEmpty()
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = false

        MapLibre.getInstance(
            this,
            null, // no API key needed
            WellKnownTileServer.MapLibre
        )

        setContent {
            GoBeyondTheme {
                val repository = CountryRepository(db.countryDao())

                val navController = rememberNavController()

                val viewModel = remember {
                    CountryViewModel(repository)
                }

                AppNavGraph(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GoBeyondTheme {
        Greeting("Android")
    }
}