package com.example.gobeyond.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.gobeyond.ui.theme.GoBeyondTheme


@Composable
fun MainScreen(
    onExploreClicked: () -> Unit = {},
    onLoginClicked: () -> Unit = {}
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "GoBeyond - Hidden Gems of Europe")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {onExploreClicked()}){
            Text(text = "Explore Destinations")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {onLoginClicked()}){
            Text(text = "Login")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    GoBeyondTheme {
        MainScreen(
            onExploreClicked = { /* Preview: do nothing */ },
            onLoginClicked = { /* Preview: do nothing */ }
        )
    }
}

