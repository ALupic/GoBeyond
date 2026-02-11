package com.example.gobeyond.ui.destinations

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

@Composable
fun DestinationScreen(destinationName: String) {
    Text(
        text = destinationName,
        fontSize = 28.sp,
        modifier = Modifier.padding(24.dp)
    )
}
