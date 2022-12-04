@file:OptIn(ExperimentalMaterial3Api::class)

package com.asknilesh.composemovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.asknilesh.composemovieapp.navigation.MovieNavigation
import com.asknilesh.composemovieapp.ui.theme.ComposeMovieAppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MovieApp()
    }
  }
}

@Composable fun MovieApp() {
  ComposeMovieAppTheme {
    MovieNavigation()

  }
}

@Preview
@Composable
fun DefaultPreview() {
  MovieApp()
}

