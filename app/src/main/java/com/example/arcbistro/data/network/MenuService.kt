package com.example.arcbistro.data.network

import com.example.arcbistro.data.MenuItem
import io.ktor.client.call.body
import io.ktor.client.request.get

class MenuService {
    private val client = KtorClient.httpClient
    private val url = "https://gist.githubusercontent.com/Husewnberg87/918602b06c3904ba6d6870ee2ca30c77/raw/cbb09db113732798d6af62725e0c51f4af04e247/menu.json"

    suspend fun fetchMenu(): List<MenuItem> {
        return client.get(url).body()
    }
}