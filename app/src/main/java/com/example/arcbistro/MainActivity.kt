package com.example.arcbistro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.arcbistro.ui.screens.BasketScreen
import com.example.arcbistro.ui.screens.HomeScreen
import com.example.arcbistro.ui.screens.ItemDetailScreen
import com.example.arcbistro.ui.screens.OnboardingScreen
import com.example.arcbistro.ui.theme.ArcBistroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArcBistroTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            OnboardingScreen(onGetStartedClicked = {
                navController.navigate("home")
            })
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("basket") {
            BasketScreen(navController = navController)
        }
        composable("favorites") { // Placeholder
            BasketScreen(navController = navController) // Replace with FavoritesScreen later
        }
        composable("notifications") { // Placeholder
            BasketScreen(navController = navController) // Replace with NotificationsScreen later
        }
        composable(
            route = "detail/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")
            if (itemId != null) {
                ItemDetailScreen(itemId = itemId, navController = navController)
            }
        }
    }
}
