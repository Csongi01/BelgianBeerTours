package com.example.guidedtours.ui.Detail

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guidedtours.R
import com.example.guidedtours.ui.theme.GuidedToursScreenTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val state = detailViewModel.detailState
    GuidedToursScreenTheme {
        Scaffold() {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "City: ${state.city}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold, color = Color.White)
                )
                Details(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),

                    state = state
                )


            }
        }
    }

}
@Composable
private fun Details(
    modifier: Modifier = Modifier,
    state: DetailState
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor =  Color(0xFF8B4513)
        ),
        border = BorderStroke(2.dp, androidx.compose.ui.graphics.Color.Black)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = getCityImageResourceId(state.city)),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp)) // Rounded corners for the image
            )

            Spacer(modifier = Modifier.height(16.dp)) // Add some spacing

            Text(
                text = "Tour guide: "+ state.tourGuide,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                ,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp)) // Add some spacing

            Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = "Beer price: ${state.beerPrice} $",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                )
            }
        }
    }
}


// Function to get the resource ID for the city image
@DrawableRes
fun getCityImageResourceId(city: String): Int {
    return when (city) {
        "Brussels" -> R.drawable.brussels
        "Ghent" -> R.drawable.ghent
        "Bruges" -> R.drawable.bruges
        "Antwerp" -> R.drawable.antwerp
        "Leuven" -> R.drawable.leuven
        "Mechelen" -> R.drawable.mechelen
        "Namur"-> R.drawable.namur
        "Ostend"-> R.drawable.ostend
        "Ypres"-> R.drawable.ypers
        "Liege"-> R.drawable.liege
        else -> R.drawable.brussels

    }
}


