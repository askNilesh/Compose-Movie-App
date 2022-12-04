package com.asknilesh.composemovieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asknilesh.composemovieapp.screen.details.DetailsScreen
import com.asknilesh.composemovieapp.screen.home.HomeScreen

@Composable
fun MovieNavigation() {
  val navController = rememberNavController()
  NavHost(
    navController = navController, startDestination = MovieScreens.HomeScreen.name,
  ) {
    composable(MovieScreens.HomeScreen.name) {
      HomeScreen(navController)
    }
    composable(
      MovieScreens.DetailsScreen.name + "/{movie}",
      arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })
    ) {
      DetailsScreen(navController,it.arguments?.getString("movie"))
    }
  }
}