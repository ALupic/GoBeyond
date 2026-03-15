package com.example.gobeyond.ui.explore

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gobeyond.R
import com.example.gobeyond.ui.model.Destination
import com.example.gobeyond.ui.theme.GoBeyondTheme

@Composable
fun DestinationListScreen(
    viewModel: DestinationViewModel,
    countryName: String,
    onDestinationClick: (String) -> Unit
) {
    val destinations by viewModel.destinations.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
        //.padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                //.statusBarsPadding()
                .padding(vertical = 32.dp, horizontal = 16.dp)
        ) {
            Text(
                text = countryName,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.padding(horizontal = 25.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {

            items(destinations){ destination ->

                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDestinationClick(destination.name) }
                ){
                    Column {
                        Image(
                            painter = painterResource(id = getDestinationImage(destination.id)),
                            contentDescription = destination.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                        )

                        Text(
                            text = destination.name,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

            }

        }

    }

    Spacer(modifier = Modifier.height(16.dp))
    Spacer(modifier = Modifier.height(16.dp))


}

@DrawableRes
fun getDestinationImage(destinationId: String): Int{
    return when (destinationId) {
        //italy
        "sanvito" -> R.drawable.sanvito
        "matera" -> R.drawable.matera
        "noto" -> R.drawable.noto
        "tropea" -> R.drawable.tropea
        "ventimiglia" -> R.drawable.ventimiglia
        "monopoli" -> R.drawable.monopoli
        //greece
        "lindos" -> R.drawable.lindos
        "symi" -> R.drawable.symi
        "prokopios" -> R.drawable.prokopios
        "metsovo" -> R.drawable.metsovo
        "olympos" -> R.drawable.olympos
        "elafonisos" -> R.drawable.elafonisos
        //france
        "menton" -> R.drawable.menton
        "carcassonne" -> R.drawable.carcassonne
        "bonifacio" -> R.drawable.bonifacio
        "eze" -> R.drawable.eze
        "riquewihr" -> R.drawable.riquewihr
        //portugal
        "sintra" -> R.drawable.sintra
        "obidos" -> R.drawable.obidos
        "guimaraes" -> R.drawable.guimaraes
        "tavira" -> R.drawable.tavira
        //spain
        "capdepera" -> R.drawable.capdepera
        "deia" -> R.drawable.deia
        "masca" -> R.drawable.masca
        "elche" -> R.drawable.elche
        "juzcar" -> R.drawable.juzcar
        "morrojable" -> R.drawable.morrojable


        else -> R.drawable.gemsofeurope_dark
    }
}

@Preview(showBackground = true)
@Composable
fun DestinationListPreview() {

    val fakeDestinations = listOf(
        Destination("rome", "Rome", "italy"),
        Destination("florence", "Florence", "italy"),
        Destination("venice", "Venice", "italy")
    )

    /*GoBeyondTheme {
        DestinationListScreen(
            destinations = fakeDestinations,
            onDestinationClick = {}
        )
    }*/
}