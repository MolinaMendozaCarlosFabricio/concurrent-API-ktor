package com.example.src.wishlist.infrastructure.adapters

import com.example.src.wishlist.domain.models.Wishlist
import com.example.src.wishlist.domain.repository.WishlistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json
import java.io.File

class WishlistDatasetManager(
    private val file: File
) : WishlistRepository {

    private val mutex = Mutex()

    override suspend fun save(wishlist: Wishlist) =
        withContext(Dispatchers.IO) {
            mutex.withLock {
                val list = if (file.exists()) {
                    Json.decodeFromString<List<Wishlist>>(file.readText())
                } else {
                    emptyList()
                }

                val updated = list + wishlist
                file.writeText(Json.encodeToString(updated))
            }
        }

    override suspend fun getByKid(kid_id: Int): List<Wishlist> =
        withContext(Dispatchers.IO) {
            mutex.withLock {
                if (!file.exists()) return@withLock emptyList()

                Json.decodeFromString<List<Wishlist>>(file.readText())
                    .filter { it.kidId == kid_id }
            }
        }
}