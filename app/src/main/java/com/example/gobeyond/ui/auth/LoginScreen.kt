package com.example.gobeyond.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onNavigateToRegister: () -> Unit
){
    var email by remember { mutableStateOf("")}
    var password by remember {mutableStateOf("")}

    Column(modifier = Modifier.padding(16.dp)) {

        Text("Login")

        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })

        Button(onClick = {
            viewModel.login(email, password)
        }) {
            Text("Login")
        }

        Text(
            text = "No account? Register",
            modifier = Modifier.clickable { onNavigateToRegister() }
        )
    }
}