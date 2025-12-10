package com.example.src.wishlist.application

import com.example.src.wishlist.domain.models.Wishlist
import com.example.src.wishlist.domain.repository.WishlistRepository
import kotlinx.coroutines.coroutineScope

class GetWishlistByKidUseCase(
    private val repository: WishlistRepository
) {
    suspend operator fun invoke(kidId: Int): List<Wishlist> =
        coroutineScope {
            repository.getByKid(kidId)
        }
}