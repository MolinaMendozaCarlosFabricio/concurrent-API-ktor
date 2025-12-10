package com.example.src.wishlist.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Wishlist(
    var id: Int,
    var objectName: String,
    var kidId: Int
)