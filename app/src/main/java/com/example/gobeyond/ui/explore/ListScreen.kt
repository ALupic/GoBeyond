package com.example.gobeyond.ui.explore

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gobeyond.ui.data.FakeCountries
import com.example.gobeyond.ui.theme.GoBeyondTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

@Composable
fun ListScreen(onCountryClick: (String) -> Unit){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(35.dp)
        ) {
            items(FakeCountries.countries) { country ->
                Button(
                    onClick = { onCountryClick(country.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors()
                ) {
                    Text(text = country.name)
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(3.dp)
        ) {
            Text(text = "Explore a country")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview(){
    GoBeyondTheme {
        ListScreen(){

        }
    }
}