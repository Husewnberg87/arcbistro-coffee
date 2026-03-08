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
            val remoteMenu = menuService.fetchMenu()
            menuDao.insertAll(remoteMenu)
        } catch (e: Exception) {
            // Log error or handle network failure
            e.printStackTrace()
        }
    }
}