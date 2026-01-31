package com.example.arcbistro.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.arcbistro.ui.theme.ArcBistroTheme

@Composable
fun BasketScreen(navController: NavController) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Text("Basket Screen - Coming Soon!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BasketScreenPreview() {
    ArcBistroTheme {
        BasketScreen(navController = rememberNavController())
    }
}
