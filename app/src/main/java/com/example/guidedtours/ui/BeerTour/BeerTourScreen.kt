package com.example.guidedtours.ui.BeerTour
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.guidedtours.model.BeerTour
import com.example.guidedtours.ui.theme.GuidedToursScreenTheme


// Import statements...

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BeerTourScreen(
    beerTourViewModel: BeerTourViewModel = viewModel(),
    FavouritesClicked: () -> Unit,
    DetailsClicked: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    var breweryId by remember{
        mutableStateOf(0)
    }
    val tours = beerTourViewModel.state.tours
    GuidedToursScreenTheme {
        Scaffold(
            modifier = Modifier,

            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier,

                    onClick = { FavouritesClicked() }
                ) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = null,
                        tint = Color.Red,
                        )
                }
            }
        ) {
            LazyColumn {
                items(tours) { beerTour ->
                    BeerTourCard(
                        beerTour = beerTour,
                        modifier = Modifier.padding(5.dp),
                        beerTourViewModel = BeerTourViewModel(),
                        DetailsClicked = DetailsClicked
                    )
                }
            }
        }
    }
}

@Composable
fun BeerTourCard(
    beerTour: BeerTour,
    modifier: Modifier = Modifier,
    beerTourViewModel: BeerTourViewModel = viewModel(),
    DetailsClicked: () -> Unit,

    ) {
    var navigate by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor =  Color(0xFF8B4513)
        ),
        border = BorderStroke(2.dp, androidx.compose.ui.graphics.Color.Black)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            // Left side (heart icon for favorites)
            HeartIcon(
                beerTour = beerTour,
                isFavorite = beerTour.isFavourite,
                beerTourViewModel
            )

            // Center part (name and description)
            Column(
                modifier = Modifier.weight(1f).padding(end = 16.dp)
            ) {
                Text(text = beerTour.name, fontWeight = FontWeight.Bold)
                Text(text = "Duration: ${beerTour.duration} hours", color = Color.Gray)
                // Add other details if needed
            }

            // Right side (rating with star icon)
            Column(
                modifier = Modifier.weight(1f).padding(start = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "${beerTour.rating}",
                        textAlign = TextAlign.End,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier.size(20.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                            .clickable {
                             beerTourViewModel.SelectedBeerTour(beerTour)
                                navigate=true
                                if(navigate)
                                {
                                    DetailsClicked.invoke()
                                }
                            }
                    )
                }
            }
        }
    }
}
@Composable
fun HeartIcon(beerTour: BeerTour, isFavorite: Boolean, beerTourViewModel: BeerTourViewModel, modifier: Modifier = Modifier) {
    Icon(
        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        contentDescription = null,
        tint = if (isFavorite) Color.Red else Color.Gray,
        modifier = Modifier
            .size(24.dp)
            .clickable {
                beerTourViewModel.MakeFavourite(beerTour)
            }
            .then(modifier)
    )
}







