package com.example.arcbistro.data

import kotlinx.coroutines.flow.Flow

class CartRepository(private val cartDao: CartDao) {

    // 1. Expose the list of all items in the basket as a Flow
    val cartItems: Flow<List<Cart>> = cartDao.getAllCartItems()

    // 2. Logic to add an item or update its quantity
    suspend fun addToCart(item: MenuItem, size: String, quantity: Int) {
        // Check if this exact coffee + size is already in the cart
        val existingItem = cartDao.getCartItem(item.id, size)

        if (existingItem != null) {
            // If it exists, update the quantity
            val updatedItem = existingItem.copy(quantity = quantity)
            if (updatedItem.quantity > 0) {
                cartDao.upsert(updatedItem)
            } else {
                cartDao.delete(existingItem)
            }
        } else if (quantity > 0) {
            // If it doesn't exist and quantity > 0, create a new entry
            val newItem = Cart(
                itemId = item.id,
                name = item.name,
                price = item.price,
                imageUrl = item.imageUrl,
                size = size,
                quantity = quantity
            )
            cartDao.upsert(newItem)
        }
    }

    suspend fun clearCart() = cartDao.clearCart()
}