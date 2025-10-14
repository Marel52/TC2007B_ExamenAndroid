package com.app.tc2007b_examenandroid.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.tc2007b_examenandroid.presentation.screens.detail.CountryDetailScreen
import com.app.tc2007b_examenandroid.presentation.screens.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onCountryClick = { countryName ->
                    navController.navigate("detail/$countryName")
                }
            )
        }

        composable(
            route = "detail/{countryName}",
            arguments = listOf(
                navArgument("countryName") { type = NavType.StringType }
            )
        ) {
            CountryDetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}