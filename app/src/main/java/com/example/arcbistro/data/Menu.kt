package com.example.arcbistro.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable // This allows Ktor to parse JSON into this object
@Entity(tableName = "menu_items") // This tells Room this is a database table
data class MenuItem(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val subtitle: String,
    val imageUrl: String, // Now a Web URL
    val rating: Double,
    val category: String
)

