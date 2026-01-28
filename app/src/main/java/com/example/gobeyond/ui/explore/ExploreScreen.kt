package com.example.gobeyond.ui.explore

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gobeyond.ui.theme.GoBeyondTheme

@Composable
fun ExploreScreen(
    onByCountryClicked: () -> Unit = {}
    //onByTypeClicked: () -> Unit = {}
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Explore Destinations")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {onByCountryClicked()}){
            Text(text = "Explore By Country")
        }

        Spacer(modifier = Modifier.height(12.dp))

        /*Button(onClick = {onByTypeClicked()}){
            Text(text = "Explore By Type")
        }*/

    }
}

@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview(){
    GoBeyondTheme{
        ExploreScreen(){

        }
    }
}