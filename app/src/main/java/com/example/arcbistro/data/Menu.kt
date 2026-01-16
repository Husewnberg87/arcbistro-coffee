package com.example.arcbistro.data

data class MenuItem(
    val name: String,
    val price: Double,
    val description: String,
){}

val menuItems = listOf(
    MenuItem("Espresso",3.5,"A strong shot of espresso"),
    MenuItem("Cappuccino",5.0,"Espresso with steamed milk and foam"),
    MenuItem("Latte",4.5,"Espresso with steamed milk"),
    MenuItem("Americano",4.0,"Espresso with hot water"),
    MenuItem("Mocha",5.5,"Espresso with chocolate syrup and milk"),
    MenuItem("Hot Chocolate",6.0,"Chocolate drink with hot milk"),
    MenuItem("Black Tea",3.0,"Refreshing tea"),
)

