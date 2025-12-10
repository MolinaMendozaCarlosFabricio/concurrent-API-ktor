package com.example.src.wishlist.domain.repository

import com.example.src.wishlist.domain.models.Wishlist

interface WishlistRepository {
    suspend fun save(wishlist: Wishlist)
    suspend fun getByKid(kid_id: Int): List<Wishlist>
}