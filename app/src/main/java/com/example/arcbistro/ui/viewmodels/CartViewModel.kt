package com.example.arcbistro.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.arcbistro.data.AppDatabase
import com.example.arcbistro.data.Cart
import com.example.arcbistro.data.CartRepository
import com.example.arcbistro.data.MenuItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository) : ViewModel() {

    // 1. Live list of items in the cart
    val cartItems: StateFlow<List<Cart>> = repository.cartItems
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // 2. Automatically calculate the total price
    val totalAmount: StateFlow<Double> = cartItems
        .map { items -> items.sumOf { it.price * it.quantity } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    /**
     * Used by Detail Screen to add a new product or update quantity by matching itemId + size.
     */
    fun updateQuantity(item: MenuItem, size: String, quantity: Int) {
        viewModelScope.launch {
            repository.addToCart(item, size, quantity)
        }
    }

    /**
     * CLEANER WAY: Used by Basket Screen to update an item that ALREADY exists in the database.
     * No need to create a "fake" MenuItem.
     */
    fun updateCartItemQuantity(cartItem: Cart, newQuantity: Int) {
        viewModelScope.launch {
            if (newQuantity > 0) {
                // Just update the quantity field of the existing object
                repository.updateCartItem(cartItem.copy(quantity = newQuantity))
            } else {
                // If it hits 0, remove it from the DB
                repository.removeFromCart(cartItem)
            }
        }
    }
}

// Factory for manual dependency injection
object CartViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
        val database = AppDatabase.getDatabase(application)
        val repository = CartRepository(database.cartDao())
        return CartViewModel(repository) as T
    }
}
