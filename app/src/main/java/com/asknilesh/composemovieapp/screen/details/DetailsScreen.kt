package com.asknilesh.composemovieapp.screen.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale.FIT
import com.asknilesh.composemovieapp.screen.home.MovieRow
import getMovies

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavHostController, movieId: String?) {
  val movie = getMovies().find { it.id == movieId }
  Scaffold(
    topBar = {
      SmallTopAppBar(
        title = {
          Text(text = "Movie")
        },
        navigationIcon = {
          IconButton(onClick = { navController.popBackStack() }) {
            Icon(
              imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
            )
          }
        }
      )
    }
  ) { padding ->

    Column(
      modifier = Modifier
        .padding(padding)
        .fillMaxWidth()
        .fillMaxHeight(
        )
    ) {
      movie?.let {
        MovieRow(it) {}
        LazyRow {
          items(movie.images) {
            Card(
              colors = CardDefaults.cardColors(Color.White),
              modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
              shape = RoundedCornerShape(10.dp),
              elevation = CardDefaults.cardElevation(5.dp)
            ) {
              AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                  .data(it)
                  .crossfade(true)
                  .scale(FIT)
                  .build(),
                contentDescription = null,
                modifier = Modifier.height(200.dp).width(150.dp)
              )
            }

          }
        }
      }
    }
  }
}