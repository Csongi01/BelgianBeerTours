package com.example.guidedtours.ui.Favourite

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guidedtours.R
import com.example.guidedtours.model.BeerTour
import com.example.guidedtours.ui.BeerTour.BeerTourViewModel
import com.example.guidedtours.ui.theme.GuidedToursScreenTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    favouriteViewModel: FavouriteViewModel = viewModel(),
    beerTourViewModel: BeerTourViewModel = viewModel(),
){


    val favouriteState = favouriteViewModel.state
    GuidedToursScreenTheme {
        Scaffold() {
            LazyColumn {
                items(favouriteState.favourites)
                {
                    val dismissState = rememberDismissState(
                        confirmValueChange = { value ->
                            if (value == DismissValue.DismissedToEnd) {
                                beerTourViewModel.MakeFavourite(it)
                            }
                            if (value == DismissValue.DismissedToStart) {
                                beerTourViewModel.MakeFavourite(it)
                            }
                            true
                        }
                    )
                    SwipeToDismiss(state = dismissState,
                        background = {
                            Surface(modifier = Modifier.fillMaxWidth()) {

                            }
                        },
                        dismissContent = {
                            FavouriteCard(
                                favorite = it,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    )

                }

            }
        }
    }
}


@Composable
fun FavouriteCard(
    favorite: BeerTour,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
            border = BorderStroke(2.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor =  Color(0xFF8B4513)
        )

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            // Left side (Image)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.beer),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = favorite.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Duration: ${favorite.duration} hours",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "${favorite.rating}",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint =  Color.Yellow
                    )
                }
            }
        }
    }
}
