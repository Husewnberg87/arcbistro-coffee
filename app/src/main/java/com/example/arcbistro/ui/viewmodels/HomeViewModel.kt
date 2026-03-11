package com.example.arcbistro.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.arcbistro.data.AppDatabase
import com.example.arcbistro.data.MenuItem
import com.example.arcbistro.data.MenuRepository
import com.example.arcbistro.data.network.MenuService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MenuRepository) : ViewModel() {

    // 1. Observe the menuItems from the Repository as a StateFlow.
    // The UI will "subscribe" to this.
    val menuItems: StateFlow<List<MenuItem>> = repository.menuItems
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        // 2. Refresh the menu from the web as soon as the app starts
        refreshMenu()
    }

    fun refreshMenu() {
        viewModelScope.launch {
            repository.refreshMenu()
        }
    }
}

object HomeViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        // Get the application context from extras
        val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
        
        // Initialize Database, Service, and Repository manually
        val database = AppDatabase.getDatabase(application)
        val service = MenuService()
        val repository = MenuRepository(database.menuDao(), service)
        
        return HomeViewModel(repository) as T
    }
}
