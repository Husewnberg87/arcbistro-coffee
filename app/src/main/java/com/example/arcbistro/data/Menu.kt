package com.example.arcbistro.data

import androidx.annotation.DrawableRes
import com.example.arcbistro.R

data class MenuItem(
    val name: String,
    val price: Double,
    val subtitle: String,
    @DrawableRes val imageRes: Int,
    val rating: Double,
    val category: String
)

val menuItems = listOf(
    MenuItem("Caffe Mocha", 4.53, "Deep Foam", R.drawable.coffee_1, 4.8, "Cappuccino"),
    MenuItem("Flat White", 3.53, "Espresso", R.drawable.coffee_2, 4.8, "Latte"),
    MenuItem("Cappuccino", 5.0, "Steamed Milk", R.drawable.coffee_3, 4.5, "Cappuccino"),
    MenuItem("Americano", 4.0, "Hot Water", R.drawable.coffee_4, 4.2, "Americano"),
    MenuItem("Latte", 4.5, "Espresso", R.drawable.coffee_5, 4.6, "Latte"),
    MenuItem("Espresso", 3.5, "Strong Shot", R.drawable.coffee_2, 4.9, "Americano"),
    MenuItem("Mocha", 5.5, "Chocolate", R.drawable.coffee_3, 4.7, "Cappuccino")
)
