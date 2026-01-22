package com.example.gobeyond

import android.os.Bundle
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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoBeyondTheme {
                val navController = rememberNavController()

                Scaffold { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        AppNavGraph(navController)
                    }
                }
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