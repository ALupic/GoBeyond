package com.example.gobeyond.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(viewModel: AuthViewModel) {

    Column(modifier = Modifier.padding(16.dp)) {

        Text("You are logged in")

        Button(onClick = {
            viewModel.logout()
        }) {
            Text("Logout")
        }
    }
}