package com.example.src.wishlist.application

import com.example.src.wishlist.domain.models.Wishlist
import com.example.src.wishlist.domain.repository.WishlistRepository
import kotlinx.coroutines.supervisorScope

class CreateWishlistUseCase(
    private val repository: WishlistRepository
) {
    suspend operator fun invoke(wishlist: Wishlist) = supervisorScope { repository.save(wishlist) }
}