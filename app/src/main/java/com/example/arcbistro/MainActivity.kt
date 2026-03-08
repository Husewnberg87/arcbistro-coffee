package com.example.arcbistro

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.arcbistro.ui.screens.BasketScreen
import com.example.arcbistro.ui.screens.DeliveryAddressScreen
import com.example.arcbistro.ui.screens.DeliveryTrackingScreen
import com.example.arcbistro.ui.screens.HomeScreen
import com.example.arcbistro.ui.screens.ItemDetailScreen
import com.example.arcbistro.ui.screens.OnboardingScreen
import com.example.arcbistro.ui.screens.OrderResultScreen
import com.example.arcbistro.ui.screens.OrderScreen
import com.example.arcbistro.ui.screens.PaymentMethodScreen
import com.example.arcbistro.ui.theme.ArcBistroTheme

class MainActivity : ComponentActivity() {

    private val sharedPreferences by lazy { getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)

        val startDestination = if (isFirstLaunch) "onboarding" else "home"

        if (isFirstLaunch) {
            sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
        }

        setContent {
            ArcBistroTheme {
                AppNavigation(startDestination = startDestination)
            }
        }
    }
}

@Composable
fun AppNavigation(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("onboarding") {
            OnboardingScreen(onGetStartedClicked = {
                navController.navigate("home") {
                    // Pop up to the start destination of the graph to remove onboarding from back stack
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
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

        // ============ Order Flow Screens ============
        composable("order") {
            OrderScreen(
                onBackClick = { navController.popBackStack() },
                onAddressClick = { navController.navigate("delivery-address") },
                onPaymentMethodClick = { navController.navigate("payment-method") },
                onOrderClick = { navController.navigate("order-result") }
            )
        }

        composable("delivery-address") {
            DeliveryAddressScreen(
                onBackClick = { navController.popBackStack() },
                onAddNewAddress = { /* TODO: Navigate to add address screen */ },
                onAddressSelected = { navController.popBackStack() }
            )
        }

        composable("payment-method") {
            PaymentMethodScreen(
                onBackClick = { navController.popBackStack() },
                onConfirm = { navController.popBackStack() }
            )
        }

        composable("order-result") {
            OrderResultScreen(
                onTrackOrder = { navController.navigate("delivery-tracking") },
                onBackToHome = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        composable("delivery-tracking") {
            DeliveryTrackingScreen(
                onBackClick = { navController.popBackStack() },
                onCallCourier = { /* TODO: Trigger phone call intent */ }
            )
        }
    }
}
