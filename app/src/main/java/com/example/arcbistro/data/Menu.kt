package com.example.arcbistro.data

import androidx.annotation.DrawableRes
import com.example.arcbistro.R

data class MenuItem(
    val id: Int ,
    val name: String,
    val price: Double,
    val subtitle: String,
    @DrawableRes val imageRes: Int,
    val rating: Double,
    val category: String
)

val menuItems = listOf(
    MenuItem(1,"Caffe Mocha", 4.53, "Deep Foam", R.drawable.coffee_1, 4.8, "Cappuccino"),
    MenuItem(2,"Flat White", 3.53, "Espresso", R.drawable.coffee_2, 4.8, "Latte"),
    MenuItem(3,"Cappuccino", 5.0, "Steamed Milk", R.drawable.coffee_3, 4.5, "Cappuccino"),
    MenuItem(4,"Americano", 4.0, "Hot Water", R.drawable.coffee_4, 4.2, "Americano"),
    MenuItem(5,"Latte", 4.5, "Espresso", R.drawable.coffee_5, 4.6, "Latte"),
    MenuItem(6,"Espresso", 3.5, "Strong Shot", R.drawable.coffee_2, 4.9, "Americano"),
    MenuItem(7,"Mocha", 5.5, "Chocolate", R.drawable.coffee_3, 4.7, "Cappuccino")
)
