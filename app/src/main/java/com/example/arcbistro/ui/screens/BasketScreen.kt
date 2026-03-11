package com.example.arcbistro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.arcbistro.ui.components.OrderItemCard
import com.example.arcbistro.ui.theme.Brown01
import com.example.arcbistro.ui.theme.DarkGray03
import com.example.arcbistro.ui.viewmodels.CartViewModel
import com.example.arcbistro.ui.viewmodels.CartViewModelFactory
import java.util.Locale

@Composable
fun BasketScreen(
    navController: NavController,
    cartViewModel: CartViewModel = viewModel(factory = CartViewModelFactory)
) {
    // 1. Observe the live state from the database
    val cartItems by cartViewModel.cartItems.collectAsState()
    val totalAmount by cartViewModel.totalAmount.collectAsState()

    Scaffold(
        topBar = {
            // Simple custom top bar
            Surface(shadowElevation = 2.dp) {
                Box(
                    modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("My Basket", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        },
        bottomBar = {
            // 2. Sticky Checkout Section
            Surface(tonalElevation = 8.dp, shadowElevation = 16.dp) {
                Column(modifier = Modifier.padding(24.dp).fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Total Price", color = DarkGray03, fontSize = 16.sp)
                        Text(
                            text = "$ ${String.format(Locale.US, "%.2f", totalAmount)}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Brown01
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { navController.navigate("order") },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Brown01)
                    ) {
                        Text("Go to Checkout", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    ) { innerPadding ->
        // 3. Main Content
        if (cartItems.isEmpty()) {
            // Empty state UI
            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Your basket is empty", color = Color.Gray)
            }
        } else {
            // 4. Reactive List of Items
            LazyColumn(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(cartItems) { item ->
                    OrderItemCard(
                        imageUrl = item.imageUrl,
                        title = item.name,
                        subtitle = "Size: ${item.size}",
                        price = item.price,
                        quantity = item.quantity,
                        onIncrease = {
                            cartViewModel.updateCartItemQuantity(item, item.quantity + 1)
                        },
                        onDecrease = {
                            cartViewModel.updateCartItemQuantity(item, item.quantity - 1)
                        }
                    )
                }
            }
        }
    }
}