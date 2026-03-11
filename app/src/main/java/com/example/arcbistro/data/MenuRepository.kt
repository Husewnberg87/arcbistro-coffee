package com.example.arcbistro.data

import com.example.arcbistro.data.network.MenuService
import kotlinx.coroutines.flow.Flow

class MenuRepository(
    private val menuDao: MenuDao,
    private val menuService: MenuService
) {
    // 1. Expose the data from Room as a Flow
    val menuItems: Flow<List<MenuItem>> = menuDao.getAllMenuItems()

    // 2. Logic to sync the remote data with local cache
    suspend fun refreshMenu() {
        try {
            println("ArcBistro: Fetching menu from web...")
            val remoteMenu = menuService.fetchMenu()
            println("ArcBistro: Success! Received ${remoteMenu.size} items.")

            menuDao.insertAll(remoteMenu)
            println("ArcBistro: Data saved to Room database.")
        } catch (e: Exception) {
            println("ArcBistro: Error fetching menu: ${e.message}")
            e.printStackTrace()
        }
    }
}