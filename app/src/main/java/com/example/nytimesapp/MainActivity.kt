package com.example.nytimesapp

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nytimesapp.ui.screen.nyarticledetail.NyTimesArticleDetailScreen
import com.example.nytimesapp.ui.screen.nyarticlelist.NyTimesScreen
import com.example.nytimesapp.ui.screen.nyarticlelist.NyTimesViewModel
import com.example.nytimesapp.ui.theme.NYTimesAppTheme
import com.example.nytimesapp.util.NavigationRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NYTimesAppTheme {
                MyApp(navController = navController)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MyApp(navController: NavHostController) {
    val viewModel: NyTimesViewModel = hiltViewModel()
    NavHost(
        navController,
        startDestination = NavigationRoute.NYTimesScreen
    ) {
        composable(NavigationRoute.NYTimesScreen) {
            NyTimesScreen(
                onItemClick = {
                    navController.navigate(NavigationRoute.NYTimesArticleDetailScreen)
                },
                viewModel = viewModel
            )
        }
        composable(NavigationRoute.NYTimesArticleDetailScreen) {
            NyTimesArticleDetailScreen(
                viewModel = viewModel
            )
        }
    }
}
