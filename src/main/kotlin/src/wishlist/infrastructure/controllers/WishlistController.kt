package com.example.src.wishlist.infrastructure.controllers

import com.example.src.wishlist.application.CreateWishlistUseCase
import com.example.src.wishlist.application.GetWishlistByKidUseCase
import com.example.src.wishlist.domain.models.Wishlist
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond
import io.ktor.server.request.receive

class WishlistController(
    private val createWishlist: CreateWishlistUseCase,
    private val getWishlistByKid: GetWishlistByKidUseCase
) {

    suspend fun create(call: ApplicationCall) {
        val wishlist = call.receive<Wishlist>()
        createWishlist(wishlist)

        call.respond(
            status = HttpStatusCode.Created,
            message = wishlist
        )
    }

    suspend fun getByKid(call: ApplicationCall) {
        val kidId = call.parameters["kidId"]?.toIntOrNull()
            ?: return call.respond(HttpStatusCode.BadRequest, "Invalid kid id")

        val list = getWishlistByKid(kidId)
        call.respond(list)
    }
}