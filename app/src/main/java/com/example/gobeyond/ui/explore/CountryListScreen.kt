package com.example.gobeyond.ui.explore

import androidx.annotation.DrawableRes
import com.example.gobeyond.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gobeyond.ui.model.Country
import com.example.gobeyond.ui.theme.GoBeyondTheme

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
        Text("Explore Countries", modifier = Modifier.padding(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {

            items(countries){ country ->

                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable{onCountryClick(country.id)}
                ){
                    Column {
                        Image(
                            painter = painterResource(id = getCountryImage(country.id)),
                            contentDescription = country.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                        )

                        Text(
                            text = country.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

            }

        }

    }
}

@DrawableRes
fun getCountryImage(countryId: String): Int{
    return when (countryId) {
        "italy" -> R.drawable.ventimiglia
        "greece" -> R.drawable.symi
        "france" -> R.drawable.carcassonne
        "spain" -> R.drawable.masca
        "portugal" -> R.drawable.obidos
        else -> R.drawable.ventimiglia
    }
}

@Preview(showBackground = true)
@Composable
fun CountryListPreview() {

    val fakeCountries = listOf(
        Country("italy", "Italy"),
        Country("spain", "Spain"),
        Country("france", "France")
    )

    GoBeyondTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Text("Explore Countries", modifier = Modifier.padding(8.dp))

            fakeCountries.forEach { country ->
                Text(
                    text = country.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
            }
        }
    }
}
