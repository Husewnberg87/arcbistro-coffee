package com.example.arcbistro.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): Flow<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(cartItem: Cart)

    @Delete
    suspend fun delete(cartItem: Cart)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    // A helper to find if a specific coffee + size is already in the basket
    @Query("SELECT * FROM cart_items WHERE itemId = :itemId AND size = :size LIMIT 1")
    suspend fun getCartItem(itemId: Int, size: String): Cart?
}