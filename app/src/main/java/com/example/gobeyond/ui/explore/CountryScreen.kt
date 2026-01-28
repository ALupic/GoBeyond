package com.example.gobeyond.ui.explore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.gobeyond.ui.data.FakeCountries
import androidx.compose.foundation.clickable
import androidx.compose.material3.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gobeyond.navigation.Routes.country
import com.example.gobeyond.ui.theme.GoBeyondTheme

@Composable
fun CountryScreen(
        countryId: String,
        onDestinationClick: (String) -> Unit
){
    val country = FakeCountries.countries.find { it.id == countryId }

    if(country == null){
        Text("Country not found")
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        item {
            Text(
                text = country.name,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        items(country.destinations) { destination ->
            Button(
                onClick = {
                    onDestinationClick(destination)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(destination)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryScreenPreview() {
    GoBeyondTheme {
        CountryScreen(countryId = "italy", onDestinationClick={})
    }
}