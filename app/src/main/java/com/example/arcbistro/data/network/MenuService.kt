package com.example.arcbistro.data.network

import com.example.arcbistro.data.MenuItem
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders

class MenuService {
    private val client = KtorClient.httpClient
    private val baseUrl = "https://gist.githubusercontent.com/Husewnberg87/918602b06c3904ba6d6870ee2ca30c77/raw/menu.json"

    suspend fun fetchMenu(): List<MenuItem> {
        // 1. Her istekte yeni bir timestamp oluşturuyoruz
        val dynamicUrl = "$baseUrl?t=${System.currentTimeMillis()}"

        return client.get(dynamicUrl) {
            // 2. Sunucuya ve ara sunuculara (CDN) cache kullanmamasını söylüyoruz
            header(HttpHeaders.CacheControl, "no-cache, no-store, must-revalidate")
            header(HttpHeaders.Pragma, "no-cache")
            header(HttpHeaders.Expires, "0")
        }.body()
    }
}