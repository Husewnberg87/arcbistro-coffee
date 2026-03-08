package com.example.arcbistro.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {
    // 1. Fetch data as a Flow.
    @Query("SELECT * FROM menu_items")
    fun getAllMenuItems(): Flow<List<MenuItem>>

    // 2. Insert data from the Web.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(menuItems: List<MenuItem>)

    @Query("DELETE FROM menu_items")
    suspend fun deleteAll()
}