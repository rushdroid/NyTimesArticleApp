package com.example.nytimesapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val myUser by lazy {
        println("Lazy initialization")
        "Hello"
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NYTimesAppTheme {
                MyApp(navController = navController)
            }
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(">>>>>>>>>>>>>>>", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            Log.d(">>>>>>>>>>>>>>>", token)
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })
        println(myUser)
        println(myUser)
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
