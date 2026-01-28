package com.example.gobeyond.ui.explore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gobeyond.ui.theme.GoBeyondTheme

@Composable
fun DestinationScreen(destinationId: String){
   /*val destination = FakeCountries.countries.find { it.id == destinationId }

    if(destination == null){
        Text("Destination not found")
        return
    }*/


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Destination: $destinationId")
    }


}

@Preview(showBackground = true)
@Composable
fun DestinationScreenPreview() {
    GoBeyondTheme {
        DestinationScreen(destinationId = "Rome")
    }
}