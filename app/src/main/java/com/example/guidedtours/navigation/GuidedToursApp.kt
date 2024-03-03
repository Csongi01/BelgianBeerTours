@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.guidedtours.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.guidedtours.R

import com.example.guidedtours.data.MyConfiguration
import com.example.guidedtours.ui.BeerTour.BeerTourScreen
import com.example.guidedtours.ui.BeerTour.BeerTourViewModel
import com.example.guidedtours.ui.Detail.DetailScreen
import com.example.guidedtours.ui.Favourite.FavouriteScreen
import com.example.guidedtours.ui.Login.LoginScreen
import com.example.guidedtours.ui.Register.RegistrationScreen

enum class GuidedToursAppScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Registration(title = R.string.register),
    ToursList(title = R.string.ListTours),
    Details(title = R.string.Details),
    FavouriteTours(title = R.string.Favourite),
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToursAppBar(
    currentScreen: GuidedToursAppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    navigateToHOme: () -> Unit,
    modifier: Modifier = Modifier,
    showLogout: Boolean

) {
    val navController = rememberNavController()
    var showDialog by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        TopAppBar(
            title = { Text(stringResource(currentScreen.title) , color = Color.White)  },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor =  Color(0xFF8B4513),

            ),
            modifier = modifier,
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button),
                            tint = Color.White
                        )
                    }
                }
            },
            actions = {
                if (showLogout) {
                    IconButton(onClick = {
                        showDialog = true

                    }) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = stringResource(R.string.logout_button),
                                    tint = Color.White
                        )
                    }
                }
            }
        )
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                ),


            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .background(color=Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Are you sure you want to logout?",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .background(color=Color.White),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = {
                                showDialog = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor =  Color(0xFF8B4513)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                        ) {
                            Text(stringResource(id = R.string.cancel))
                        }
                        Button(
                            onClick = {
                                navigateToHOme.invoke()
                                showDialog = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor =  Color(0xFF8B4513)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            Text(stringResource(id = R.string.confirm))
                        }
                    }
                }
            }
        }
    }
}
    @Composable
    fun GuidedToursApp(navController: NavHostController = rememberNavController()) {


        val backStackEntry by navController.currentBackStackEntryAsState()
        val beerTourViewModel: BeerTourViewModel = viewModel()


        val currentScreen = GuidedToursAppScreen.valueOf(
            backStackEntry?.destination?.route ?: GuidedToursAppScreen.Start.name
        )
        var showLogoutAction by remember {
            mutableStateOf(false)
        }
        val breweryId by remember {
            mutableStateOf(-1)
        }
        Scaffold(
            topBar = {
                ToursAppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = {
                        if (navController.previousBackStackEntry?.destination?.route.equals(
                                GuidedToursAppScreen.Start.name
                            )
                        ) {
                            //reset loggedinUser
                            MyConfiguration.loggedInUser = null
                        }
                        navController.navigateUp()
                    },
                    showLogout = showLogoutAction, navigateToHOme = {
                        if (navController.previousBackStackEntry?.destination?.route.equals(
                                GuidedToursAppScreen.Start.name
                            )
                        ) {
                            MyConfiguration.loggedInUser = null
                        }
                        navController.navigate(route = GuidedToursAppScreen.Start.name)

                    }
                )


            }
        ) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = GuidedToursAppScreen.Start.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = GuidedToursAppScreen.Start.name) {

                    showLogoutAction = false
                    LoginScreen(
                        loginSuccessFull = {
                            navController.navigate(GuidedToursAppScreen.ToursList.name)
                        },
                        Register = {
                            navController.navigate(GuidedToursAppScreen.Registration.name)
                        }

                    )

                }

                composable(route = GuidedToursAppScreen.Registration.name) {
                    showLogoutAction = false
                    RegistrationScreen(
                        loginSuccessFull = {
                            navController.navigate(GuidedToursAppScreen.Start.name)
                        }
                    )

                }
                composable(route = GuidedToursAppScreen.ToursList.name) {
                    showLogoutAction = true
                    BeerTourScreen(
                        FavouritesClicked = {
                            navController.navigate(GuidedToursAppScreen.FavouriteTours.name)
                        },
                        DetailsClicked = {
                            navController.navigate(GuidedToursAppScreen.Details.name)
                        }


                    )
                }

                composable(route = GuidedToursAppScreen.Details.name) {

                    DetailScreen()

                }

                composable(route = GuidedToursAppScreen.FavouriteTours.name) {
                    showLogoutAction = true
                    FavouriteScreen()

                }
            }
        }
    }
