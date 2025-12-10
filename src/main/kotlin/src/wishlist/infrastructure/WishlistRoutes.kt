package com.example.src.wishlist.infrastructure


import com.example.src.wishlist.infrastructure.controllers.WishlistController
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import io.ktor.server.routing.get
import io.ktor.server.routing.post

fun Route.wishlistRoutes(controller: WishlistController) {

    route("/wishlist") {

        post {
            controller.create(call)
        }

        get("/kid/{kidId}") {
            controller.getByKid(call)
        }
    }
}