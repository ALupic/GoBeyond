package com.example.gobeyond.ui.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CountryListScreen(
    viewModel: CountryViewModel,
    onCountryClick: (String) -> Unit
) {
    val countries by viewModel.countries.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text("Explore Countriesssss", modifier = Modifier.padding(8.dp))

        LazyColumn {
            items(countries) { country ->

                Text(
                    text = country.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCountryClick(country.id)
                        }
                        .padding(12.dp)
                )
            }
        }

    }
}
