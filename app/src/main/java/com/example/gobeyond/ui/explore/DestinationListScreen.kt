package com.example.gobeyond.ui.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun DestinationListScreen(
    viewModel: DestinationViewModel
) {
    val destinations by viewModel.destinations.collectAsState()

    Column {
        destinations.forEach {
            Text(it.name)
        }
    }
}
