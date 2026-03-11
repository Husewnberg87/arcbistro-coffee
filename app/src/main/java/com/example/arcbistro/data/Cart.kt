package com.example.arcbistro.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class Cart(
    @PrimaryKey(autoGenerate = true) val cartId: Int = 0, // Unique ID for the database
    val itemId: Int,      // ID of the coffee (links to MenuItem)
    val name: String,    // Storing name/price/image directly makes the UI faster later
    val price: Double,
    val imageUrl: String,
    val size: String,    // "S", "M", or "L"
    val quantity: Int    // Current amount
)