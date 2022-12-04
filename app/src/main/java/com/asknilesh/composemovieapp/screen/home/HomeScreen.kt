package com.asknilesh.composemovieapp.screen.home

import Movie
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.asknilesh.composemovieapp.R.drawable
import com.asknilesh.composemovieapp.navigation.MovieScreens
import getMovies

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
  Scaffold(topBar = {
    SmallTopAppBar(
      title = {
        Text(
          "Movies", maxLines = 1, overflow = TextOverflow.Ellipsis
        )

      }, colors = TopAppBarDefaults.smallTopAppBarColors(Color.Blue)
    )
  }) { padding ->
    MainContent(modifier = Modifier.padding(padding), navController = navController)
  }
}

@Composable fun MainContent(
  modifier: Modifier = Modifier,
  navController: NavController, moviesList: List<Movie> = getMovies()
) {
  Column(modifier = modifier) {
    LazyColumn {
      items(moviesList) { movieName ->
        MovieRow(movieName) { movie ->
          navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movie")
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRow(movie: Movie, onMovieClick: (String) -> Unit) {

  var expanded by remember {
    mutableStateOf(false)
  }

  Card(
    onClick = {
      onMovieClick(movie.id)
    },
    colors = CardDefaults.cardColors(Color.White),
    modifier = Modifier
      .padding(10.dp)
      .fillMaxWidth(),
    shape = RoundedCornerShape(10.dp),
    elevation = CardDefaults.cardElevation(5.dp)
  ) {
    Row(
      verticalAlignment = Alignment.Top,
      horizontalArrangement = Arrangement.Center
    ) {

      Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceEvenly
      ) {

        Image(
          painter = painterResource(drawable.hulk),
          contentDescription = "Movie Image",
          modifier = Modifier.height(100.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
          modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
        ) {
          Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
          Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.bodyMedium)
          Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.bodyMedium)
          Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.bodyMedium)
          Text(text = "Year: ${movie.year}", style = MaterialTheme.typography.bodyMedium)

          AnimatedVisibility(visible = expanded) {
            Column {
              Text(text = buildAnnotatedString {
                withStyle(
                  style = SpanStyle(
                    fontSize = 13.sp
                  )
                ) {
                  append("Plot: ")
                }
                withStyle(
                  style = SpanStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                  )
                ) {
                  append(movie.plot)
                }
              })
            }
          }
        }
        IconButton(onClick = { expanded = !expanded }) {
          Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else
              Icons.Filled.KeyboardArrowDown, contentDescription = ""
          )
        }
      }

    }

  }
}